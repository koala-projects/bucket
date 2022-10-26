package cn.koala.bucket.mybatis;

import cn.koala.bucket.JdbcHelper;
import cn.koala.bucket.Report;
import cn.koala.bucket.ReportService;
import cn.koala.mybatis.AbstractUUIDCrudService;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 报表服务MyBatis实现
 *
 * @author Houtaroy
 */
@Data
@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
public class ReportServiceImpl extends AbstractUUIDCrudService<Report> implements ReportService {
  private final ReportRepository repository;

  @Override
  public List<Map<String, Object>> getData(String id, Map<String, String> parameters) {
    Optional<Report> optionalReport = repository.findById(id);
    if (optionalReport.isEmpty()) {
      return new ArrayList<>();
    }
    Report report = optionalReport.get();
    try {
      return JdbcHelper.query(report.getDataSource().getConnection(), generateSql(report.getSql(), parameters));
    } catch (SQLException e) {
      throw new RuntimeException("SQL查询失败", e);
    }
  }

  protected String generateSql(String sql, Map<String, String> parameters) {
    String result = sql;
    for (String key : parameters.keySet()) {
      result = result.replace(key, parameters.get(key));
    }
    return result;
  }
}

package cn.koala.bucket;

import cn.koala.persistence.CrudService;

import java.util.List;
import java.util.Map;

/**
 * 报表服务
 *
 * @author Houtaroy
 */
public interface ReportService extends CrudService<String, Report> {
  /**
   * 获取报表数据
   *
   * @param id         报表id
   * @param parameters 查询参数
   * @return 报表数据
   */
  List<Map<String, Object>> getData(String id, Map<String, String> parameters);
}

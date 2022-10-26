package cn.koala.bucket;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 报表数据实体
 *
 * @author Houtaroy
 */
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class ReportEntity implements Report {
  private String id;
  private String code;
  private String name;
  private String description;
  private DataSourceEntity dataSource;
  private String sql;
  private String config;
}

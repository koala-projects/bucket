package cn.koala.bucket;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "报表数据实体")
public class ReportEntity implements Report {
  @Schema(description = "报表ID")
  private String id;
  @Schema(description = "报表代码")
  private String code;
  @Schema(description = "报表名称")
  private String name;
  @Schema(description = "报表描述")
  private String description;
  @Schema(description = "报表数据源")
  private DataSourceEntity dataSource;
  @Schema(description = "报表查询SQL语句")
  private String selectSql;
  @Schema(description = "报表配置")
  private String config;
}

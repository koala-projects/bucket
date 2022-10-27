package cn.koala.bucket;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 数据源数据实体
 *
 * @author Houtaroy
 */
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Schema(description = "数据源数据实体")
public class DataSourceEntity implements DataSource {
  @Schema(description = "数据源ID")
  private String id;
  @Schema(description = "数据源代码")
  private String code;
  @Schema(description = "数据源名称")
  private String name;
  @Schema(description = "数据源描述")
  private String description;
  @Schema(description = "数据源连接")
  private String url;
  @Schema(description = "数据源用户名")
  private String username;
  @Schema(description = "数据源密码")
  private String password;
}

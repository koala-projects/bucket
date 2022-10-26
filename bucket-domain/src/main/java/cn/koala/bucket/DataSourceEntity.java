package cn.koala.bucket;

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
public class DataSourceEntity implements DataSource {
  private String id;
  private String code;
  private String name;
  private String description;
  private String url;
  private String username;
  private String password;
}

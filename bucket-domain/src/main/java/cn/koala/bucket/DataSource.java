package cn.koala.bucket;

import cn.koala.persistence.Codeable;
import cn.koala.persistence.Idable;

/**
 * 数据源
 *
 * @author Houtaroy
 */
public interface DataSource extends Idable<String>, Codeable {
  /**
   * 获取数据库url
   *
   * @return 数据库url
   */
  String getUrl();

  /**
   * 获取数据库用户名
   *
   * @return 数据库用户名
   */
  String getUsername();

  /**
   * 获取数据库密码
   *
   * @return 数据库密码
   */
  String getPassword();
}

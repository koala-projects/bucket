package cn.koala.bucket;

import cn.koala.persistence.Codeable;
import cn.koala.persistence.Idable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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

  /**
   * 获取数据库连接
   *
   * @return 数据库连接
   * @throws SQLException SQLException
   */
  default Connection getConnection() throws SQLException {
    return DriverManager.getConnection(getUrl(), getUsername(), getPassword());
  }
}

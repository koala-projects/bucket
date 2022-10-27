package cn.koala.bucket;

import cn.koala.persistence.Codeable;
import cn.koala.persistence.Idable;

/**
 * 报表
 *
 * @author Houtaroy
 */
public interface Report extends Idable<String>, Codeable {
  /**
   * 获取数据源
   *
   * @return 数据源
   */
  DataSource getDataSource();

  /**
   * 获取SQL
   *
   * @return SQL
   */
  String getSelectSql();

  /**
   * 获取报表配置
   *
   * @return 报表配置
   */
  Object getConfig();
}

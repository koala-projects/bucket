package cn.koala.bucket;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * ResultSet列数据解析函数式接口
 *
 * @author Houtaroy
 */
public interface ResultSetValueParser {
  /**
   * 解析数据
   *
   * @param set         结果数据集
   * @param columnIndex 列索引
   * @return 列数据
   * @throws SQLException SQLException
   */
  Object parse(ResultSet set, int columnIndex) throws SQLException;
}

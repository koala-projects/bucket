package cn.koala.bucket;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Jdbc助手
 *
 * @author Houtaroy
 */
public abstract class JdbcHelper {

  public static final Map<Integer, ResultSetValueParser> RESULT_SET_VALUE_PARSERS = new HashMap<>();

  static {
    RESULT_SET_VALUE_PARSERS.put(Types.DATE, ResultSet::getDate);
    RESULT_SET_VALUE_PARSERS.put(Types.BOOLEAN, ResultSet::getBoolean);
    RESULT_SET_VALUE_PARSERS.put(Types.BLOB, ResultSet::getBlob);
  }

  /**
   * 查询
   *
   * @param connection 数据库连接
   * @param sql        查询语句
   * @return 查询结果
   * @throws SQLException SQLException
   */
  public static List<Map<String, Object>> query(Connection connection, String sql) throws SQLException {
    try (Statement statement = connection.createStatement(); ResultSet set = statement.executeQuery(sql)) {
      List<Map<String, Object>> result = new ArrayList<>();
      while (set.next()) {
        result.add(getRow(set));
      }
      return result;
    }
  }

  /**
   * 获取列数据
   *
   * @param set 查询结果集
   * @return 列数据
   * @throws SQLException SQLException
   */
  public static Map<String, Object> getRow(ResultSet set) throws SQLException {
    ResultSetMetaData metaData = set.getMetaData();
    int columnCount = metaData.getColumnCount();
    Map<String, Object> result = new HashMap<>(columnCount);
    for (int i = 1; i <= columnCount; i++) {
      int columnType = set.getMetaData().getColumnType(i);
      ResultSetValueParser parser = RESULT_SET_VALUE_PARSERS.getOrDefault(columnType, ResultSet::getString);
      result.put(metaData.getColumnName(i), parser.parse(set, i));
    }
    return result;
  }
}

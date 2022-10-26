package cn.koala.bucket;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 数据源服务内存实现
 *
 * @author Houtaroy
 */
public class InMemoryDataSourceService implements DataSourceService {
  private final Map<String, DataSource> dataSources = new HashMap<>();

  @Override
  public Page<DataSource> list(Map<String, Object> parameters, Pageable pageable) {
    throw new UnsupportedOperationException("当前数据源服务不支持分页查询");
  }

  @Override
  public List<DataSource> list(Map<String, Object> parameters) {
    throw new UnsupportedOperationException("当前数据源服务不支持查询");
  }

  @Override
  public Optional<DataSource> load(String id) {
    return Optional.ofNullable(dataSources.get(id));
  }

  @Override
  public void add(DataSource entity) {
    dataSources.put(entity.getId(), entity);
  }

  @Override
  public void update(DataSource entity) {
    dataSources.put(entity.getId(), entity);
  }

  @Override
  public void delete(DataSource entity) {
    dataSources.remove(entity.getId());
  }
}

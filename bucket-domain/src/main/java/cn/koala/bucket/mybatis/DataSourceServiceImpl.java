package cn.koala.bucket.mybatis;

import cn.koala.bucket.DataSource;
import cn.koala.bucket.DataSourceService;
import cn.koala.mybatis.AbstractUUIDCrudService;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

/**
 * 数据源服务MyBatis实现
 *
 * @author Houtaroy
 */
@Data
@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
public class DataSourceServiceImpl extends AbstractUUIDCrudService<DataSource> implements DataSourceService {
  private final DataSourceRepository repository;
}

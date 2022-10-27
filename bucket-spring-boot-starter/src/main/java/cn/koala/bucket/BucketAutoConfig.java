package cn.koala.bucket;

import cn.koala.bucket.mybatis.DataSourceRepository;
import cn.koala.bucket.mybatis.DataSourceServiceImpl;
import cn.koala.bucket.mybatis.ReportRepository;
import cn.koala.bucket.mybatis.ReportServiceImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 水桶自动配置
 *
 * @author Houtaroy
 */
@Configuration
@MapperScan("cn.koala.bucket.mybatis")
public class BucketAutoConfig {
  /**
   * 数据源服务的bean
   *
   * @param dataSourceRepository 数据源存储库
   * @return 数据源服务对象
   */
  @Bean
  @ConditionalOnMissingBean
  public DataSourceService dataSourceService(DataSourceRepository dataSourceRepository) {
    return new DataSourceServiceImpl(dataSourceRepository);
  }

  /**
   * 数据源管理接口的bean
   *
   * @param dataSourceService 数据源服务对象
   * @return 数据源管理接口对象
   */
  @Bean
  @ConditionalOnMissingBean
  public DataSourceApi dataSourceApi(DataSourceService dataSourceService) {
    return new DataSourceApiImpl(dataSourceService);
  }

  /**
   * 报表服务的bean
   *
   * @param reportRepository 报表存储库
   * @return 报表服务对象
   */
  @Bean
  @ConditionalOnMissingBean
  public ReportService reportService(ReportRepository reportRepository) {
    return new ReportServiceImpl(reportRepository);
  }

  /**
   * 报表管理接口的bean
   *
   * @param reportService 报表服务对象
   * @return 报表管理接口对象
   */
  @Bean
  @ConditionalOnMissingBean
  public ReportApi reportApi(ReportService reportService) {
    return new ReportApiImpl(reportService);
  }
}

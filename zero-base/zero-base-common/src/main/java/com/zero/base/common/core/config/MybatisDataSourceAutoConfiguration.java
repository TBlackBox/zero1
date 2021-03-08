
package com.zero.base.common.core.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.zero.base.common.core.config.properties.DruidProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MybatisPlus配置
 *
 * @author yongzhi
 * @Date 2017/5/20 21:58
 */
@Configuration
@ConditionalOnProperty(prefix = "spring.datasource", name = "url")
public class MybatisDataSourceAutoConfiguration {

    /**
     * druid配置
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DruidProperties druidProperties() {
        return new DruidProperties();
    }

    /**
     * druid数据库连接池
     */
    @Bean(initMethod = "init")
    public DruidDataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        druidProperties().config(dataSource);
        return dataSource;
    }

}

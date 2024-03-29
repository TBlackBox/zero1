//
//package com.zero.manager.config.datasource;
//
//import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
//import com.zero.base.common.core.config.properties.DruidProperties;
//import com.zero.base.common.core.mutidatasource.mybatis.OptionalSqlSessionTemplate;
//import com.zero.base.common.db.collector.SqlSessionFactoryCreator;
//import com.zero.base.common.db.context.DataSourceContext;
//import com.zero.base.common.db.context.SqlSessionFactoryContext;
//import com.zero.base.common.db.exception.DataSourceInitException;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Import;
//import org.springframework.context.annotation.Primary;
//
//import javax.sql.DataSource;
//
//
///**
// * 多数据源配置<br/>
// * <p>
// * 注：由于引入多数据源，所以让spring事务的aop要在多数据源切换aop的后面
// *
// * @author yongzhi
// * @Date 2017/5/20 21:58
// */
//@Slf4j
//@Configuration
//@Import(SqlSessionFactoryCreator.class)
//public class SqlSessionFactoryConfig {
//
//    /**
//     * 主sqlSessionFactory
//     */
//    @Primary
//    @Bean
//    public SqlSessionFactory sqlSessionFactoryPrimary(@Qualifier("dataSourcePrimary") DataSource dataSource,
//                                                      SqlSessionFactoryCreator sqlSessionFactoryCreator) {
//        return sqlSessionFactoryCreator.createSqlSessionFactory(dataSource);
//    }
//
//    /**
//     * 多数据源sqlSessionTemplate切换模板
//     */
//    @Bean(name = "gunsSqlSessionTemplate")
//    public OptionalSqlSessionTemplate gunsSqlSessionTemplate(@Qualifier("dataSourcePrimary") DataSource dataSourcePrimary,
//                                                             @Qualifier("sqlSessionFactoryPrimary") SqlSessionFactory sqlSessionFactoryPrimary,
//                                                             SqlSessionFactoryCreator sqlSessionFactoryCreator,
//                                                             DruidProperties druidProperties) {
//        //初始化数据源容器
//        try {
//            DataSourceContext.initDataSource(druidProperties, dataSourcePrimary);
//        } catch (Exception e) {
//            log.error("初始化数据源容器错误!", e);
//            throw new DataSourceInitException(DataSourceInitException.ExEnum.INIT_DATA_SOURCE_ERROR);
//        }
//
//        //添加主数据源的SqlSessionFactory
//        SqlSessionFactoryContext.addSqlSessionFactory(DataSourceContext.MASTER_DATASOURCE_NAME, sqlSessionFactoryPrimary);
//
//        //初始化其他数据源的SqlSessionFactory的容器
//        SqlSessionFactoryContext.initBaseSqlSessionFactory(sqlSessionFactoryCreator);
//
//        //设置SqlHelper为主数据源
//        SqlHelper.FACTORY = sqlSessionFactoryPrimary;
//
//        return new OptionalSqlSessionTemplate(sqlSessionFactoryPrimary, SqlSessionFactoryContext.getSqlSessionFactorys());
//    }
//
//}
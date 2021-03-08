//
//package com.zero.base.common.core.config;
//
//import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
//import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
//import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
//import com.zero.base.common.core.datascope.DataScopeInterceptor;
//import com.zero.base.common.core.dbid.GunsDatabaseIdProvider;
//import com.zero.base.common.core.metadata.CustomMetaObjectHandler;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * MybatisPlus配置
// *
// * @author yongzhi
// * @Date 2017/5/20 21:58
// */
//@Configuration
//@ConditionalOnProperty(prefix = "spring.datasource", name = "url")
//public class MybatisPluginAutoConfiguration {
//
//    /**
//     * mybatis-plus分页插件
//     */
//    @Bean
//    public PaginationInterceptor paginationInterceptor() {
//        return new PaginationInterceptor();
//    }
//
//    /**
//     * 自定义公共字段自动注入
//     */
//    @Bean
//    @ConditionalOnMissingBean
//    public MetaObjectHandler metaObjectHandler() {
//        return new CustomMetaObjectHandler();
//    }
//
//    /**
//     * 数据范围mybatis插件
//     */
//    @Bean
//    @ConditionalOnMissingBean
//    public DataScopeInterceptor dataScopeInterceptor() {
//        return new DataScopeInterceptor();
//    }
//
//    /**
//     * 乐观锁mybatis插件
//     */
//    @Bean
//    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
//        return new OptimisticLockerInterceptor();
//    }
//
//    /**
//     * 数据库id选择器
//     */
//    @Bean
//    @ConditionalOnMissingBean
//    public GunsDatabaseIdProvider gunsDatabaseIdProvider() {
//        return new GunsDatabaseIdProvider();
//    }
//
//}

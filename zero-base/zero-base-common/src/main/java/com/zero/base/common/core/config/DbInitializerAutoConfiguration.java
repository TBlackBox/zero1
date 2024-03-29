
package com.zero.base.common.core.config;

import com.zero.base.common.core.db.listener.InitTableListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 数据库初始化默认配置
 *
 * @author fengshuonan
 * @Date 2018/7/30 下午12:22
 */
@Configuration
public class DbInitializerAutoConfiguration {

    @Bean
    public InitTableListener initTableListener() {
        return new InitTableListener();
    }
}



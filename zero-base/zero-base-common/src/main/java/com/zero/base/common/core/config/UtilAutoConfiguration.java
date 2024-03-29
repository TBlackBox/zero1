
package com.zero.base.common.core.config;

import com.zero.base.common.core.util.SpringContextHolder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 默认的工具类
 *
 * @author fengshuonan
 * @date 2018-01-07 12:33
 */
@Configuration
public class UtilAutoConfiguration {

    @Bean
    public SpringContextHolder springContextHolder() {
        return new SpringContextHolder();
    }
}

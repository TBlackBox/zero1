package com.zero.manager;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * WEB 程序启动类
 * @Author yongzhi
 * @Date 2021/3/5 14:18
 **/
public class ZeroServletInitializer extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ZeroServletInitializer.class);
    }
}

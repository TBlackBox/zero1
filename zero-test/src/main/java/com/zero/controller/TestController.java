package com.zero.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * @Author yongzhi
 * @Date 2021/3/8 14:31
 **/
@RestController
public class TestController {

    @GetMapping("/test")
    public Object test(){

        return "测试：" + LocalDateTime.now();
    }
}

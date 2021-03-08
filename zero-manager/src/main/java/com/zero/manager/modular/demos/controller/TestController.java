package com.zero.manager.modular.demos.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author yongzhi
 * @Date 2021/3/5 15:08
 **/
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/test")
    public Object tets(){

        return "测试";
    }
}

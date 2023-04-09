package com.lk.Hello.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//自动装配的核心原理
@RestController
public class HelloController {
    //接口：http://localhost:8080/hello
    @RequestMapping("/hello")
    //调用业务 ,接受前端的参数
    public String hello(){
        return "hello,world";
    }
}

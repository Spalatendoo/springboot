package com.lk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

//在templates 目录下的所有页面，稚嫩通过Controller来跳转
//需要模板引擎的支持  thymeleaf
@Controller
public class indexController {

    @RequestMapping({"/","index.html"})
    public String index(){
        return "index";
    }
}

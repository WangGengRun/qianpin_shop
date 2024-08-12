package com.itbuka.oauth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    @RequestMapping("/findAll")
    public String findAll(){
        return "产品列表信息...";
    }
}


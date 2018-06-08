package com.claylin.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
public class CustomerController {

    @RequestMapping("/index")
    public String index(){
        return "hello spring boot";
    }
}

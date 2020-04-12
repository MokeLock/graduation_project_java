package com.mokelock.graduation_project.controller;

import com.mokelock.graduation_project.services.MainServices;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class MainController {

    @Resource
    MainServices mainServices;

    @RequestMapping("/")
    public String hello() {
        return "hello";
    }

    @RequestMapping("/getMsg")
    public String getMsg() {
        return mainServices.getMsgFromDocker();
    }

}

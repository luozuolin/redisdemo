package com.example.redisdemo.controller;

import com.example.redisdemo.service.Test1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class Test1Controller {
    @Autowired
    Test1Service  test1Service;
    @GetMapping("/test1/getData1/{param}")
    public  String  getData1(@PathVariable("param") String param) throws Exception {
        return test1Service.search1(param);
    }
    @GetMapping("/test1/getData2/{param}")
    public  String  getData2(@PathVariable("param") String param) throws Exception {

        return test1Service.search2(param);
    }
    @GetMapping("/test1/updateData1/{param}")
    public  String  updateData1(@PathVariable("param") String param) throws Exception {

        return test1Service.updateData1(param);
    }
}

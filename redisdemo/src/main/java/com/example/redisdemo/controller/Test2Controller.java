package com.example.redisdemo.controller;

import com.example.redisdemo.service.Test2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class Test2Controller {
    @Autowired
    Test2Service test2Service;
    @GetMapping("/test2/getData1/{param}")
    public  String  getData1(@PathVariable("param") String param){
        return test2Service.search1(param);
    }
    @GetMapping("/test2/getData2/{param}")
    public  String  getData2(@PathVariable("param") String param) throws Exception {
        return test2Service.search2(param);
    }
    @GetMapping("/test2/updateData1/{param}")
    public  String  updateData1(@PathVariable("param") String param) throws Exception {

        return test2Service.updateData1(param);
    }

}

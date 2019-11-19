package com.example.redisdemo.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class Test1Service {

    public  String search1(String param){
        return param+(new Random()).nextInt()+"(Test1Service search1)";
    }
    public  String search2(String param){
        return param+(new Random()).nextInt()+"(Test1Service search1)";
    }
    public String updateData1(String param){
        return search1(param);
    }
}

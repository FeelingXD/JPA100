package com.example.sample1.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class SecondController {


    @ResponseBody
    @RequestMapping(value = "/hello-spring", method = RequestMethod.GET)
    public String helloSpring() {
        return "hello spring";
    }

    @GetMapping("/hello-rest")
    public String helloRest(){
        return "hello rest";
    }

    @GetMapping("/api/helloworld")
    public String helloRestApi(){
        return "hello rest api";
    }


}

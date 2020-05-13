package com.yx.home.netty.controller;

import com.yx.home.netty.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/hello")
@RestController
public class HelloController {
    @Autowired
    private RedisUtils redisUtils;

    @GetMapping("/{name}")
    public String hello(@PathVariable("name") String name) {
        redisUtils.setValue("name", name);
        System.out.println(redisUtils.getValue("name"));

        return "hello, " + name;
    }
}

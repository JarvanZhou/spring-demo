package com.jarvan.springdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: spring-demo
 * @description:
 * @author: jarvan
 * @create: 2021-03-21
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String say(@RequestParam("name") String name) {
        return "hello " + name;
    }
}

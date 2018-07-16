package com.demo.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/employee")
public class BaseEmployeeController {
    @RequestMapping("/basic")
    public String basic() {
        return "basic";
    }

    @RequestMapping("/")
    public String hello() {
        return "Hello";
    }
}

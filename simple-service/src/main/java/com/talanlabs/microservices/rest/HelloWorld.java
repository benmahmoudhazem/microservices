package com.talanlabs.microservices.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {

    public static final String HELLO_WORLD = "Hello World!";

    @RequestMapping("/")
    String home() {
        return HELLO_WORLD;
    }

}
package com.talanlabs.microservices.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class HelloWorld {

    @RequestMapping("/")
    String home() {
        "Hello World!"
    }

}
package com.example.springbootapp.api;

import com.example.springbootapp.utils.Constants;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello-world")
public class HelloWorldController {

    @GetMapping(value = "/hello-world")
    public String helloWorld() {
        return Constants.HELLO_WORLD;
    }
}

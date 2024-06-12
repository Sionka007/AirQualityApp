package com.rest.webservices.restfulwebservices.controller;


import com.rest.webservices.restfulwebservices.entity.HelloWorldBean;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class HelloWorldController {

    @GetMapping(value = "/hello-world/{name}")
    public HelloWorldBean helloWorldBean(@PathVariable String name){
        return new HelloWorldBean(String.format("Hello World, %s", name));
    }
}

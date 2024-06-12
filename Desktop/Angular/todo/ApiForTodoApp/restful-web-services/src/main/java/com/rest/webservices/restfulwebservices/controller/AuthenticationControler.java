package com.rest.webservices.restfulwebservices.controller;

import com.rest.webservices.restfulwebservices.entity.AuthenticationBeam;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
public class AuthenticationControler {

    @GetMapping(value = "/basicauth")
    public AuthenticationBeam helloBeam(){
        return new AuthenticationBeam("You are authenticated");
    }
}

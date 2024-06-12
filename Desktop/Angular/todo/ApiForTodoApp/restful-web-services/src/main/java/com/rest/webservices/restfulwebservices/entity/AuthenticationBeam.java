package com.rest.webservices.restfulwebservices.entity;

public class AuthenticationBeam {
    private String message;
    public AuthenticationBeam(String message) {
        this.message=message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

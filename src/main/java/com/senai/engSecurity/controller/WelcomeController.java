package com.senai.engSecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class WelcomeController {

    @GetMapping
    public String welcome() {
        return "Hello World";
    }

    @GetMapping("/user")
    public String user() {
        return "Authorize USER";
    }

    @GetMapping("/adm")
    public String adm() {
        return "Authorize ADM";
    }

    @GetMapping("/public")
    public String publicEndpoint() {
        return "Publicly accessible endpoint";
    }
}
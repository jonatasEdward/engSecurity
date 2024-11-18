package com.senai.engSecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class WelcomeController {

    @GetMapping("/public")
    public String publicEndpoint() {
        return "Este é um endpoint público.";
    }

    @GetMapping("/user")
    public String userEndpoint() {
        return "Este é um endpoint restrito para usuários autenticados.";
    }

    @GetMapping("/admin")
    public String adminEndpoint() {
        return "Este é um endpoint restrito para administradores.";
    }
}
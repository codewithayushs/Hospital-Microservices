package com.hms.authservice.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hms.authservice.dto.LoginRequest;
import com.hms.authservice.service.JwtService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtService jwtService;

    public AuthController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @GetMapping("/test")
    public String test() {
        return "Auth Service Running";
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody LoginRequest request) {

        Map<String, String> response = new HashMap<>();

        if (request.getUsername().equals("ayush") &&
                request.getPassword().equals("1234")) {

            String token = jwtService.generateToken(request.getUsername());
            response.put("token", token);
            return response;
        }

        response.put("message", "Invalid username or password");
        return response;
    }
}
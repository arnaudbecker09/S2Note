package com.arnau.S2Note.controller;

import com.arnau.S2Note.service.JwtService;
import com.arnau.S2Note.model.LoginRequest;
import com.arnau.S2Note.model.LoginResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtService jwtService;

    public AuthController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        Map<String, String> users = Map.of(
                "alice@mymail.com", "verysecure",
                "bob@mymail.com", "notencrypted"
        );

        if (!users.containsKey(request.email()) ||
                !users.get(request.email()).equals(request.password())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtService.generateToken(request.email());
        return new LoginResponse(token);
    }
}

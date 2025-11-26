package com.arnau.S2Note;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtService jwtService;

    public AuthController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {

        if (!request.email().equals("test@test.com") ||
                !request.password().equals("password")) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtService.generateToken(request.email());
        return new LoginResponse(token);
    }
}

package com.solo_finance_app.auth.controllers;

import com.solo_finance_app.auth.dto.AuthResponse;
import com.solo_finance_app.auth.dto.LoginRequest;
import com.solo_finance_app.auth.dto.RegisterRequest;
import com.solo_finance_app.auth.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private final AuthService authService;

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {

        return "User Registered Successfully";
    }

    @PostMapping("/login")
    public AuthResponse login(
            @RequestBody LoginRequest request) {

        return authService.login(request);
    }
}

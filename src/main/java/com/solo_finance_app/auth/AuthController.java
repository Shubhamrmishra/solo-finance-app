package com.solo_finance_app.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {

        return "User Registered Successfully";
    }
}

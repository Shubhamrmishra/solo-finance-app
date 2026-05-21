package com.solo_finance_app.test;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping
    public String test() {

        return "Protected API Working";
    }
}

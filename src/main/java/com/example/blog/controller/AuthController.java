package com.example.blog.controller;

import com.example.blog.payload.ApiResponse;
import com.example.blog.payload.UserModel;
import com.example.blog.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public UserModel register(@RequestBody UserModel userModel){
        return authService.register(userModel);
    }

    @PostMapping("/login")
    public ApiResponse login(@RequestBody UserModel userModel){
        return authService.login(userModel);
    }

}

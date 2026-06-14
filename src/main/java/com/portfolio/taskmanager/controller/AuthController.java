package com.portfolio.taskmanager.controller;


import com.portfolio.taskmanager.dto.AuthRequest;
import com.portfolio.taskmanager.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @PostMapping("/register")
    public String register(@RequestBody AuthRequest request){
        return authService.register(request);
    }
    @PostMapping("/login")
    public String login(@RequestBody AuthRequest request){
       return authService.login(request);
    }
}

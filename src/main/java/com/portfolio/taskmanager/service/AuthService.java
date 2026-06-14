package com.portfolio.taskmanager.service;


import com.portfolio.taskmanager.dto.AuthRequest;
import com.portfolio.taskmanager.model.User;
import com.portfolio.taskmanager.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    public String register(AuthRequest request){
        User user=new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        return "User Registered Successfully";
    }
    public String login(AuthRequest request){
        User user=userRepository.findByEmail(request.getEmail()).orElseThrow(()->new RuntimeException("User not Found"));
        if(!passwordEncoder.matches(request.getPassword(),user.getPassword())){
            throw new RuntimeException("Wrong password");
        }
        return jwtService.generateToken(user.getEmail());
    }
}

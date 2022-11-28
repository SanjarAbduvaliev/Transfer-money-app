package com.example.paymeapp.controller;

import com.example.paymeapp.payload.LoginDto;
import com.example.paymeapp.security.jwt.JwtProvider;
import com.example.paymeapp.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    MyUserDetailsService myUserDetailsService;

    @PostMapping("/login")
    public HttpEntity<?> loginToSystem(@RequestBody LoginDto loginDto) {
        try {

            UserDetails userDetails = myUserDetailsService.loadUserByUsername(loginDto.getPhoneNumber());

            boolean matches = passwordEncoder.matches(loginDto.getPassword(), passwordEncoder.encode(userDetails.getPassword()));
            if (matches) {
                String token = jwtProvider.generateToken(userDetails.getUsername());
                return ResponseEntity.ok(token);
            }
            return ResponseEntity.status(401).body("login yoki parol xato");
        }catch (Exception e){
            return ResponseEntity.status(404).body("User topilmadi!");
        }
    }
}

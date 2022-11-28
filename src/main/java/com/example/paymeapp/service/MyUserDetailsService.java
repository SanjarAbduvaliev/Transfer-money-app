package com.example.paymeapp.service;

import com.example.paymeapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    com.example.paymeapp.entity.User user;
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {

        if (userRepository.existsByPhoneNumber(username)) {
            com.example.paymeapp.entity.User selectUser = userRepository.findByPhoneNumber(username);
            return new User(selectUser.getPhoneNumber(), selectUser.getPassword(), new ArrayList<>());
        }

        throw new UsernameNotFoundException("Bunday user  mavjud emas");
    }
}

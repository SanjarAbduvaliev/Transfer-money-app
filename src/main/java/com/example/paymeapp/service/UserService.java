package com.example.paymeapp.service;

import com.example.paymeapp.entity.Card;
import com.example.paymeapp.entity.User;
import com.example.paymeapp.payload.UserDto;
import com.example.paymeapp.repository.CardRepository;
import com.example.paymeapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CardRepository cardRepository;

    public ResponseEntity<?> addUser(UserDto userDto){
        if (userRepository.existsByPhoneNumberNot(userDto.getPhoneNumber()))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Telefon raqam avval ro'yhatdan o'tgan");
        User user=new User();
        user.setFullName(userDto.getFullName());
        //PASSWORDNI ENCOLAB BAZAGA SAQLAYMIZ
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setPhoneNumber(userDto.getPhoneNumber());
        List<Card> cards = cardRepository.findAllById(userDto.getCardsId());

        user.setCards(cards);
        userRepository.save(user);
        return ResponseEntity.ok("User ro'yhatga olindi");


    }

    public ResponseEntity<?> editUser(UserDto userDto){
        User user = userRepository.findByPhoneNumber(userDto.getPhoneNumber());
        user.setFullName(userDto.getFullName());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setPhoneNumber(userDto.getNewPhoneNumber());
        return ResponseEntity.ok("User tahrirlandi");
    }
    public ResponseEntity<?> getUsers(){
        List<User> users = userRepository.findAll();
        return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(users);
    }
    public ResponseEntity<?> getUserId(Integer id){
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent())
            return ResponseEntity.ok(optionalUser.get());
        return ResponseEntity.status(404).body("Bunday user mavjudmas");
    }
}

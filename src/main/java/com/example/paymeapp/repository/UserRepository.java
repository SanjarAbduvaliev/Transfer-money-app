package com.example.paymeapp.repository;

import com.example.paymeapp.entity.Card;
import com.example.paymeapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {

    boolean existsByPhoneNumberNot(String phoneNumber);
    boolean existsByPhoneNumber(String phoneNumber);

    User findByPhoneNumber(String phoneNumber);



}

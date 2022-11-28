package com.example.paymeapp.repository;

import com.example.paymeapp.entity.Card;
import com.example.paymeapp.entity.Income;
import com.example.paymeapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CardRepository extends JpaRepository<Card,Integer> {
    boolean existsByCardNumberNot(String cardNumber);



    public Card findByCardNumber(String cardNumber);

}

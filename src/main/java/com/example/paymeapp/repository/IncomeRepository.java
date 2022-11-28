package com.example.paymeapp.repository;

import com.example.paymeapp.entity.Income;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IncomeRepository extends JpaRepository<Income,Integer> {
}

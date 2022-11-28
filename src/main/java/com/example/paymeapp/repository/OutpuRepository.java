package com.example.paymeapp.repository;

import com.example.paymeapp.entity.Income;
import com.example.paymeapp.entity.Output;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OutpuRepository extends JpaRepository<Output,Integer> {
}

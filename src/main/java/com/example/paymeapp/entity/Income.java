package com.example.paymeapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(nullable = false)
    private Double summa;
    @Column(nullable = false)
    private Date incomeDate;
    @Column(nullable = false)
    private String comment;
//    private Object fromWhom;

    public Income(Double summa, Date incomeDate, String comment) {

        this.summa = summa;
        this.incomeDate = incomeDate;
        this.comment = comment;
    }
}

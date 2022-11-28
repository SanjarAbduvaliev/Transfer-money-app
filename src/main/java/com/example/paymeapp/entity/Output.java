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
public class Output {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private Double summa;
    @Column(nullable = false)
    private Date outputDate;
    private Double comission;
    private String comment;


    public Output(Double summa, Date outputDate, String comment, Object fromWhom,Double comission) {
        this.summa = summa;
        this.outputDate = outputDate;
        this.comment = comment;
        this.comission = comission;
    }
}

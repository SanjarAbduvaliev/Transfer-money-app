package com.example.paymeapp.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class OutputDto {
    @NotEmpty(message = "Mablag'ni kiriting")
    private Double summa;
    private Integer from;
    @NotBlank(message = "Karta raqamaningii kiriting")
    private String fromCardNumber;
    private String comment;
    @NotBlank(message = "Kartani tanlang")
    private Integer myCardIndex;
}

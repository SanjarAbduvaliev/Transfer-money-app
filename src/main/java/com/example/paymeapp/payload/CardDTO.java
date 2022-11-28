package com.example.paymeapp.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class CardDTO {
    @NotEmpty(message = "Ismni kiriting")
    private String name;
    @NotBlank(message = "Karta raqami kiritilishi kerak")
    private String cardNumber;
    private Date expireDate;

}

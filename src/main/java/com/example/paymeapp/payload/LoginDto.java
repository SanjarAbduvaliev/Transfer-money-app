package com.example.paymeapp.payload;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class LoginDto {
    @NotBlank(message = "Telefon  raqamaningizni kiriting")
    private String phoneNumber;
    @NotBlank(message = "Parolni kiriting")
    private String password;
}

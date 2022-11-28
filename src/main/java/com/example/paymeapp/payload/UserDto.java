package com.example.paymeapp.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
public class UserDto {
    @NotEmpty(message = "FIO kiriting")
    private String fullName;
    @NotBlank(message = "Telefon  raqamaningizni kiriting")
    private String phoneNumber;
    @NotBlank(message = "Yangi  telefon raqamni kiriting kiriting")
    private String newPhoneNumber;
    @NotBlank(message = "Parolni kiriting")
    private String password;
    @NotEmpty(message = "Kartani tanlang")
    private Set<Integer> cardsId;
}

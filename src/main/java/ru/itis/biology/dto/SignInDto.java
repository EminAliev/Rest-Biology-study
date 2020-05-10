package ru.itis.biology.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class SignInDto {

    @NotNull
    @Email
    private String email;

    @NotNull
    @Size(min = 5, max = 22)
    private String password;
}

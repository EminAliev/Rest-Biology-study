package ru.itis.biology.service;


import ru.itis.biology.dto.SignInDto;
import ru.itis.biology.dto.TokenDto;

public interface SignInService {
    TokenDto signIn(SignInDto signInData);
}

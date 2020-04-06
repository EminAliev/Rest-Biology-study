package ru.itis.biology.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.biology.dto.SignInDto;
import ru.itis.biology.dto.TokenDto;
import ru.itis.biology.service.SignInService;

@RestController
@RequestMapping("/api/signIn")
public class SignInRestController {

    @Autowired
    private SignInService signInService;

    @PostMapping
    public ResponseEntity<TokenDto> signIn(@RequestBody SignInDto signInDto) {
        return ResponseEntity.ok(signInService.signIn(signInDto));
    }

}

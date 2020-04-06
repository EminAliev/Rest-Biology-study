package ru.itis.biology.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.biology.dto.SignUpDto;
import ru.itis.biology.service.SignUpService;

@RestController
@RequestMapping("/api/signUp")
public class SignUpRestController {

    @Autowired
    private SignUpService signUpService;

    @PostMapping
    public ResponseEntity<?> signUp(@RequestBody SignUpDto signUpDto) {
        signUpService.signUp(signUpDto);
        return ResponseEntity.accepted().build();
    }
}

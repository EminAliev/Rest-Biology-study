package ru.itis.biology.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.biology.service.ConfirmService;

@RestController
public class ConfirmRestController {

    @Autowired
    private ConfirmService confirmService;

    @GetMapping("/api/confirm/{confirm-code}")
    public ResponseEntity<?> confirm(@PathVariable("confirm-code") String confirmCode) {
        if (confirmCode != null) {
            confirmService.confirm(confirmCode);
            return ResponseEntity.accepted().build();
        }
        return null;
    }

}

package ru.itis.biology.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.itis.biology.dto.ThemeDto;
import ru.itis.biology.service.TeacherService;
import ru.itis.biology.service.ThemeService;


import java.util.List;

@RestController
@RequestMapping("/api/theme")
public class ThemeRestController {

    @Autowired
    private ThemeService themeService;

    @Autowired
    private TeacherService teacherService;


    @GetMapping()
    public ResponseEntity<List<ThemeDto>> getThemePage() {
        return new ResponseEntity<>(themeService.listThemes(), HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> proposeTheme(ThemeDto form) {
        teacherService.addTheme(form);
        return ResponseEntity.accepted().build();
    }
}

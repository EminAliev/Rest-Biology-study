package ru.itis.biology.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.itis.biology.models.Question;
import ru.itis.biology.service.QuestionService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/test")
public class QuestionRestController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/{test-id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<Question>> getProduct(@PathVariable("test-id") Long testId) {
        return ResponseEntity.ok(questionService.getTestsByThemeId(testId));
    }
}

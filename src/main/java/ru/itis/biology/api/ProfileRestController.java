package ru.itis.biology.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.biology.models.User;
import ru.itis.biology.security.jwt.details.UserDetailsImpl;
import ru.itis.biology.service.UsersService;

@RestController
@RequestMapping("/api/profile")
public class ProfileRestController {

    @Autowired
    private UsersService usersService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public ResponseEntity<User> getSelf() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getDetails();
        User user = usersService.getUserbyId(userDetails.getUserId());
        return ResponseEntity.ok(User.builder()
                .name(user.getName())
                .fullname(user.getFullname())
                .email(user.getEmail())
                .classNumber(user.getClassNumber())
                .phone(user.getPhone())
                .id(user.getId())
                .build());
    }

}

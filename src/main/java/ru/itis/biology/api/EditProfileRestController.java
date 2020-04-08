package ru.itis.biology.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.biology.dto.UserDto;
import ru.itis.biology.security.jwt.details.UserDetailsImpl;
import ru.itis.biology.service.ProfileService;


@RestController
public class EditProfileRestController {

    @Autowired
    private ProfileService profileService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/api/editProfile")
    public String editProfile(UserDto userDto, Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        profileService.editProfile(userDto, userDetails.getUser());
        return "redirect:/profile";
    }


}

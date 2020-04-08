package ru.itis.biology.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.biology.models.User;
import ru.itis.biology.security.jwt.details.UserDetailsImpl;
import ru.itis.biology.service.FileStorageService;
import ru.itis.biology.service.UsersService;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/storage")
public class StorageRestController {

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private UsersService usersService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/files")
    public ResponseEntity<?> handleImageUpload(@RequestParam("file") MultipartFile file) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        fileStorageService.saveImage(authentication, file);
        return ResponseEntity.accepted().build();
    }

    /*// URL-для получения файла
    @GetMapping("/files/{file-name:.+}")
    public void getFile(@PathVariable("file-name") String fileName,
                        HttpServletResponse response) {
        // запись файла в ответ
        service.writeFileToResponse(fileName, response);
    }*/

}

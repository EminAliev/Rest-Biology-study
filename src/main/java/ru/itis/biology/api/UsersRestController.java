package ru.itis.biology.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.itis.biology.dto.UserDto;
import ru.itis.biology.service.UsersService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@PreAuthorize("hasAuthority('ADMIN')")
public class UsersRestController {

    @Autowired
    private UsersService usersService;

    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers() {
        return ResponseEntity.ok(usersService.getUsers());
    }

    @GetMapping("/{user-id}")
    public ResponseEntity<UserDto> getConcreteUserPage(@PathVariable("user-id") Long userId) {
        return ResponseEntity.ok(usersService.getConcreteUser(userId));
    }

    @DeleteMapping("/{user-id}")
    public ResponseEntity<?> deleteUser(@PathVariable("user-id") Long userId) {
        usersService.deleteUser(userId);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<UserDto>> searchUsers(@RequestParam("name") String name) {
        return ResponseEntity.ok(usersService.search(name));
    }

}

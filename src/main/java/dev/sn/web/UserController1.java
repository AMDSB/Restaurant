package dev.sn.web;

import dev.sn.entities.User1;
import dev.sn.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController1 {
    private final UserService userService;

    public UserController1(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<User1> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User1 currentUser = (User1) authentication.getPrincipal();

        return ResponseEntity.ok(currentUser);
    }

    @GetMapping("/")
    public ResponseEntity<List<User1>> allUsers() {
        List<User1> users = userService.allUsers();

        return ResponseEntity.ok(users);
    }
}

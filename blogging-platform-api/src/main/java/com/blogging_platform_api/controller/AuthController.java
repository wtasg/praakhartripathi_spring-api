package com.blogging_platform_api.controller;

import com.blogging_platform_api.DTO.LoginRequest;
import com.blogging_platform_api.DTO.RegisterRequest;
import com.blogging_platform_api.entity.User;
import com.blogging_platform_api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register (@Valid @RequestBody RegisterRequest request) {
        userService.registerUser(request);
        return ResponseEntity.status((HttpStatus.CREATED))
                .body("User Registered successfully");
    }

    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        User user = userService.login(request);

        return ResponseEntity.ok(
                Map.of(
                        "message", "Login successfully",
                        "userId", user.getId(),
                        "email", user.getEmail(),
                        "username", user.getUsername(),
                        "role", user.getRole()
                )
        );
    }
}

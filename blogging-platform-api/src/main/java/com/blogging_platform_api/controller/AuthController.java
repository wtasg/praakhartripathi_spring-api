package com.blogging_platform_api.controller;

import com.blogging_platform_api.DTO.BlogResponse;
import com.blogging_platform_api.DTO.LoginRequest;
import com.blogging_platform_api.DTO.RegisterRequest;
import com.blogging_platform_api.DTO.UserProfileResponse;
import com.blogging_platform_api.entity.User;
import com.blogging_platform_api.service.BlogService;
import com.blogging_platform_api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;
    private final BlogService blogService;

    public AuthController(UserService userService, BlogService blogService) {
        this.userService = userService;
        this.blogService = blogService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register (@Valid @RequestBody RegisterRequest request) {
        userService.registerUser(request);
        return ResponseEntity.status((HttpStatus.CREATED))
                .body("User Registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        Map<String, Object> response = userService.login(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/me")
    public ResponseEntity<UserProfileResponse> getMyProfile(Authentication authentication) {
        String email = authentication.getName();
        UserProfileResponse response = userService.getLoggedInUserProfile(email);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserProfileResponse> getUserById(@PathVariable Long userId) {
        UserProfileResponse response = userService.getUserById(userId);
        return ResponseEntity.ok(response);
    }
}

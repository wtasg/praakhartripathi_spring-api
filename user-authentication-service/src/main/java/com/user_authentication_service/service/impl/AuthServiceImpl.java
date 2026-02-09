package com.user_authentication_service.service.impl;

import com.user_authentication_service.dto.ForgotPasswordRequest;
import com.user_authentication_service.dto.LoginRequest;
import com.user_authentication_service.dto.RegisterRequest;
import com.user_authentication_service.dto.UpdatePasswordRequest;
import com.user_authentication_service.entity.User;
import com.user_authentication_service.entity.enums.Role;
import com.user_authentication_service.repository.UserRepository;
import com.user_authentication_service.security.JwtUtil;
import com.user_authentication_service.service.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public User register(RegisterRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("username already exits");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);

        return userRepository.save(user);
    }

    @Override
    public String login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        if (authentication.isAuthenticated()) {
            return jwtUtil.generateToken(request.getUsername());
        } else {
            throw new RuntimeException("invalid credentials");
        }
    }

    @Override
    public void forgotPassword(ForgotPasswordRequest request) {
        Optional<User> userOptional = userRepository.findByUsername(request.getUsername());
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found with username: " + request.getUsername());
        }
        User user = userOptional.get();
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
    }

    @Override
    public void updatePassword(UpdatePasswordRequest request) {
        Optional<User> userOptional = userRepository.findByUsername(request.getUsername());
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found with username: " + request.getUsername());
        }
        User user = userOptional.get();
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid current password");
        }
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
    }
}

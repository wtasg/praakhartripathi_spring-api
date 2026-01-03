package com.user_authentication_service.service;

import com.user_authentication_service.dto.ForgotPasswordRequest;
import com.user_authentication_service.dto.LoginRequest;
import com.user_authentication_service.dto.RegisterRequest;
import com.user_authentication_service.dto.UpdatePasswordRequest;
import com.user_authentication_service.entity.User;

public interface AuthService {
    User register(RegisterRequest request);
    String login(LoginRequest request);
    void forgotPassword(ForgotPasswordRequest request);
    void updatePassword(UpdatePasswordRequest request);
}

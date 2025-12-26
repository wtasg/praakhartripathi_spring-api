package com.blogging_platform_api.service;

import com.blogging_platform_api.DTO.LoginRequest;
import com.blogging_platform_api.DTO.RegisterRequest;
import com.blogging_platform_api.entity.User;

public interface UserService {
    void registerUser(RegisterRequest request);
    User login(LoginRequest request);
}

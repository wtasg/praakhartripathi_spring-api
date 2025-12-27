package com.blogging_platform_api.service;

import com.blogging_platform_api.DTO.LoginRequest;
import com.blogging_platform_api.DTO.RegisterRequest;
import com.blogging_platform_api.DTO.UserProfileResponse;
import com.blogging_platform_api.entity.User;

import java.util.Map;

public interface UserService {
    void registerUser(RegisterRequest request);
    Map<String, Object> login(LoginRequest request);
    UserProfileResponse getLoggedInUserProfile(String email);

}

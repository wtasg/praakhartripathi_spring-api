package com.user_authentication_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegisterRequest {
    @NotBlank(message = "username is required")
    @Size(min = 3, max = 20, message = "username must by between 3 and 20 characters")
    private String username;
    @NotBlank(message = "password is required")
    @Size(min = 6, message = "password must be at least 6 characters and less then 32 character")
    private String password;

    public RegisterRequest() {
    }

    public RegisterRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

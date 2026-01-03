package com.user_authentication_service.dto;

import jakarta.validation.constraints.NotBlank;

public class ForgotPasswordRequest {
    @NotBlank(message = "username is required")
    private String username;
    @NotBlank(message = "new password is required")
    private String newPassword;

    public ForgotPasswordRequest() {
    }

    public ForgotPasswordRequest(String username, String newPassword) {
        this.username = username;
        this.newPassword = newPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}

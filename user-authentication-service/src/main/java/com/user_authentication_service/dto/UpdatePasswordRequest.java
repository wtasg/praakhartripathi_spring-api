package com.user_authentication_service.dto;

import jakarta.validation.constraints.NotBlank;

public class UpdatePasswordRequest {
    @NotBlank(message = "username is required")
    private String username;
    @NotBlank(message = "current password is required")
    private String currentPassword;
    @NotBlank(message = "new password is required")
    private String newPassword;

    public UpdatePasswordRequest() {
    }

    public UpdatePasswordRequest(String username, String currentPassword, String newPassword) {
        this.username = username;
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}

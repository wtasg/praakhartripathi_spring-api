package com.otp_login.model;

import java.time.LocalDateTime;

public class OtpData {
    private String otp;
    private LocalDateTime expiryTime;

    public OtpData() {
    }

    public OtpData(String otp, LocalDateTime expiryTime) {
        this.otp = otp;
        this.expiryTime = expiryTime;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public LocalDateTime getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(LocalDateTime expiryTime) {
        this.expiryTime = expiryTime;
    }
}

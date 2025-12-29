package com.otp_login.controller;

import com.otp_login.service.OtpService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final OtpService otpService;

    public AuthController(OtpService otpService) {
        this.otpService = otpService;
    }

    @PostMapping("/send-otp")
    public ResponseEntity<String> sendOtp(@RequestBody Map<String, String> requestBody) {
        String phone = requestBody.get("phone");
        otpService.generateOtp(phone);
        return ResponseEntity.ok("otp sent successfully");
    }

    @PostMapping("/verify/otp")
    public ResponseEntity<String> verifyOtp(@RequestBody Map<String, String> requestBody) {
        String phone = requestBody.get("phone");
        String otp = requestBody.get("otp");

        boolean isVerified = otpService.verifyOtp(phone, otp);

        if (isVerified) {
            return ResponseEntity.ok("Login successfully");
        } else {
            return ResponseEntity.status(401).body("Invalid or expired OTP");
        }
    }
}

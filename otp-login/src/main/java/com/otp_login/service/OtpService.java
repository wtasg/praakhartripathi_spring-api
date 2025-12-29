package com.otp_login.service;

import com.otp_login.model.OtpData;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;


@Service
public class OtpService {
    private final Map<String, OtpData> otpStore = new ConcurrentHashMap<>();

    public void generateOtp(String phone) {
        String otp = String.valueOf(100000 + new Random().nextInt(900000));
        LocalDateTime expiryTime = LocalDateTime.now().plusMinutes(1);
        OtpData otpData = new OtpData(otp, expiryTime);
        otpStore.put(phone, otpData);
        System.out.println("OTP for " + phone + " is: " + otp);
    }

    public boolean verifyOtp(String phone, String otp) {
        OtpData storedOtpData = otpStore.get(phone);
        if(storedOtpData == null) {
            return false;
        }

        if (LocalDateTime.now().isAfter(storedOtpData.getExpiryTime())) {
            return false;
        }

        boolean isOtpMatched = storedOtpData.getOtp().equals(otp);

        if (isOtpMatched) {
            otpStore.remove(phone);
        }
        return isOtpMatched;
    }
}

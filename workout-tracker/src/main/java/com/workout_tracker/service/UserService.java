package com.workout_tracker.service;

import com.workout_tracker.dto.*;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<MessageResponse> registerUser(SignupRequest signUpRequest);
    ResponseEntity<JwtResponse> authenticateUser(LoginRequest loginRequest);
    ResponseEntity<MessageResponse> updatePassword(UpdatePasswordRequest updatePasswordRequest);
    ResponseEntity<MessageResponse> forgotPassword(ForgotPasswordRequest forgotPasswordRequest);
    ResponseEntity<UserProfileResponse> getCurrentUser();
    ResponseEntity<MessageResponse> updateProfile(UpdateProfileRequest updateProfileRequest);
}

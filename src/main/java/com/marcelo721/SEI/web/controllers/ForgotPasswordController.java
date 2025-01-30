package com.marcelo721.SEI.web.controllers;
import com.marcelo721.SEI.services.ForgotPasswordService;
import com.marcelo721.SEI.web.dto.forgotPasswordDto.ResetPasswordDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/forgot-password")
@RequiredArgsConstructor
public class ForgotPasswordController {

    private final ForgotPasswordService forgotPasswordService;

    @PostMapping("/verifyMail/{email}")
    public ResponseEntity<String> verifyMail(@PathVariable String email) {
        forgotPasswordService.verifyEmail(email);
        return ResponseEntity.ok("OTP verified");
    }

    @PostMapping("/verifyOtp/{otp}/{email}")
    public ResponseEntity<String> verifyOTP(@PathVariable Integer otp, @PathVariable String email) {
        forgotPasswordService.verifyOTP(otp, email);
        return ResponseEntity.ok("OTP verified");
    }

    @PostMapping("/changePassword/{email}")
    public ResponseEntity<?> changePassword(@PathVariable String email, @RequestBody ResetPasswordDto resetPasswordDto) {

        forgotPasswordService.changePassword(email, resetPasswordDto);
        return ResponseEntity.ok("password has been changed");
    }

}

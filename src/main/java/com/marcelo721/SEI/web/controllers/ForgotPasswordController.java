package com.marcelo721.SEI.web.controllers;
import com.marcelo721.SEI.services.ForgotPasswordService;
import com.marcelo721.SEI.web.dto.SubjectDto.SubjectResponseDto;
import com.marcelo721.SEI.web.dto.forgotPasswordDto.OtpDto;
import com.marcelo721.SEI.web.dto.forgotPasswordDto.ResetPasswordDto;
import com.marcelo721.SEI.web.exceptions.ErrorMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/forgot-password")
@RequiredArgsConstructor
public class ForgotPasswordController {

    private final ForgotPasswordService forgotPasswordService;

    @Operation(
            summary = "verify email",
            description = "Endpoint to verify email.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OTP verified",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = SubjectResponseDto.class))),
                    @ApiResponse(responseCode = "404", description = "User not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))}
    )
    @PostMapping("/verifyMail/{email}")
    public ResponseEntity<String> verifyMail(@PathVariable String email) {
        forgotPasswordService.verifyEmail(email);
        return ResponseEntity.ok("OTP verified");
    }

    @Operation(
            summary = "verify opt",
            description = "Endpoint to verify otp in database.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "successfully",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = SubjectResponseDto.class))),
                    @ApiResponse(responseCode = "417", description = "OTp expired",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "404", description = "OTP not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            }
    )
    @PostMapping("/verifyOtp/{email}")
    public ResponseEntity<String> verifyOTP(@RequestBody OtpDto otpDto, @PathVariable String email) {
        forgotPasswordService.verifyOTP(otpDto.OTP(), email);
        return ResponseEntity.ok("OTP verified");
    }


    @Operation(
            summary = "change password",
            description = "Endpoint to password.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "successfully",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = SubjectResponseDto.class))),
                    @ApiResponse(responseCode = "404", description = "user not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "422", description = "invalid data",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            }
    )
    @PostMapping("/changePassword/{email}")
    public ResponseEntity<?> changePassword(@PathVariable String email, @RequestBody ResetPasswordDto resetPasswordDto) {

        forgotPasswordService.changePassword(email, resetPasswordDto);
        return ResponseEntity.ok("password has been changed");
    }

}

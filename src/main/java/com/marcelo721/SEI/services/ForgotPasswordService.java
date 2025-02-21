package com.marcelo721.SEI.services;

import com.marcelo721.SEI.entities.ForgotPassword;
import com.marcelo721.SEI.entities.User;
import com.marcelo721.SEI.repositories.ForgotPasswordRepository;
import com.marcelo721.SEI.services.exceptions.EntityNotFoundException;
import com.marcelo721.SEI.services.exceptions.OtpExpiredException;
import com.marcelo721.SEI.services.exceptions.PasswordInvalidException;
import com.marcelo721.SEI.services.record.MailBody;
import com.marcelo721.SEI.web.dto.forgotPasswordDto.ResetPasswordDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;
import java.util.Objects;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class ForgotPasswordService {

    private final ForgotPasswordRepository forgotPasswordRepository;
    private final UserService userService;
    private final EmailResetPasswordService emailResetPasswordService;


    @Transactional
    public void verifyEmail(String email) {
        User userEntity = userService.findByEmail(email);

        // generating OTP
        Integer otp = generateOTP();

        // Create an HTML-formatted email body
        String htmlContent = "<html><body>"
                + "<h2>Password Reset Request</h2>"
                + "<p>Hello,</p>"
                + "<p>You requested to reset your password. Please use the following OTP to reset your password:</p>"
                + "<h3 style='color: blue;'>" + otp + "</h3>"
                + "<p>This OTP will expire in 1 minute.</p>"
                + "<p>If you did not request this, please ignore this email.</p>"
                + "<p>Best regards,</p>"
                + "<p>Your support team</p>"
                + "</body></html>";

        MailBody mailBody = MailBody.builder()
                .to(email)
                .text(htmlContent)  // Sending HTML content in the email body
                .subject("OTP Forgot Password Request")
                .build();

        ForgotPassword forgotPassword = ForgotPassword.builder()
                .otp(otp)
                .expireDate(new Date(System.currentTimeMillis() + 70 * 1000))  // OTP expiration time
                .user(userEntity)
                .build();

        emailResetPasswordService.sendMail(mailBody);
        forgotPasswordRepository.save(forgotPassword);
    }

    @Transactional()
    public void verifyOTP(Integer otp, String email) {
        User userEntity = userService.findByEmail(email);

        ForgotPassword forgotPassword = forgotPasswordRepository.findByOtpAndUser(otp, userEntity).
                orElseThrow(() -> new EntityNotFoundException("OTP not found"));

        if (forgotPassword.getExpireDate().before(Date.from(Instant.now()))) {
            forgotPasswordRepository.deleteById(forgotPassword.getId());
            throw new OtpExpiredException("OTP expired");
        }

    }

    @Transactional()
    public void changePassword(String email, ResetPasswordDto resetPasswordDto) {

        if (!Objects.equals(resetPasswordDto.password(), resetPasswordDto.repeatPassword())) {
            throw new PasswordInvalidException("password and confirm password do not match");
        }

        User user = userService.findByEmail(email);
        user.setPassword(resetPasswordDto.password());
        userService.save(user);
    }

    private Integer generateOTP() {
        Random random = new Random();
        return random.nextInt(100_000, 999_999);
    }
}

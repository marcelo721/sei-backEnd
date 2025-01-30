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


    @Transactional()
    public void verifyEmail(String email) {
        User userEntity = userService.findByEmail(email);

        //generating OTP
        Integer otp = generateOTP();

        MailBody mailBody = MailBody.builder()
                .to(email)
                .text("this is the opt")
                .subject("OTP forgot password request" + otp)
                .build();

        ForgotPassword forgotPassword = ForgotPassword.builder()
                .otp(otp)
                .expireDate(new Date(System.currentTimeMillis() + 70 * 1000))
                .user(userEntity).build();

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

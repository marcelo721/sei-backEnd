package com.marcelo721.SEI.unitTest.serviceTest;

import com.marcelo721.SEI.entities.ForgotPassword;
import com.marcelo721.SEI.entities.User;
import com.marcelo721.SEI.repositories.ForgotPasswordRepository;
import com.marcelo721.SEI.services.EmailResetPasswordService;
import com.marcelo721.SEI.services.ForgotPasswordService;
import com.marcelo721.SEI.services.UserService;
import com.marcelo721.SEI.services.exceptions.EntityNotFoundException;
import com.marcelo721.SEI.services.exceptions.OtpExpiredException;
import com.marcelo721.SEI.services.exceptions.PasswordInvalidException;
import com.marcelo721.SEI.services.record.MailBody;
import com.marcelo721.SEI.web.dto.forgotPasswordDto.ResetPasswordDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ForgotPasswordServiceTest {

    @Mock
    private ForgotPasswordRepository forgotPasswordRepository;

    @Mock
    private UserService userService;

    @Mock
    private EmailResetPasswordService emailResetPasswordService;

    @InjectMocks
    private ForgotPasswordService forgotPasswordService;

    private User user;
    private ForgotPassword forgotPassword;
    private ResetPasswordDto resetPasswordDto;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setEmail("user@example.com");

        forgotPassword = new ForgotPassword();
        forgotPassword.setId(1);
        forgotPassword.setOtp(123456);
        forgotPassword.setExpireDate(Date.from(Instant.now().plusSeconds(70))); // OTP válido
        forgotPassword.setUser(user);

        resetPasswordDto = new ResetPasswordDto("newPassword", "newPassword");
    }

    @Test
    void verifyEmail_ShouldSendEmailAndSaveOTP() {
        when(userService.findByEmail("user@example.com")).thenReturn(user);
        doNothing().when(emailResetPasswordService).sendMail(any(MailBody.class));
        when(forgotPasswordRepository.save(any(ForgotPassword.class))).thenReturn(forgotPassword);

        forgotPasswordService.verifyEmail("user@example.com");

        verify(userService, times(1)).findByEmail("user@example.com");
        verify(emailResetPasswordService, times(1)).sendMail(any(MailBody.class));
        verify(forgotPasswordRepository, times(1)).save(any(ForgotPassword.class));
    }

    @Test
    void verifyOTP_ShouldNotThrowExceptionWhenOTPIsValid() {
        when(userService.findByEmail("user@example.com")).thenReturn(user);
        when(forgotPasswordRepository.findByOtpAndUser(123456, user)).thenReturn(Optional.of(forgotPassword));

        assertDoesNotThrow(() -> forgotPasswordService.verifyOTP(123456, "user@example.com"));

        verify(userService, times(1)).findByEmail("user@example.com");
        verify(forgotPasswordRepository, times(1)).findByOtpAndUser(123456, user);
    }

    @Test
    void verifyOTP_ShouldThrowEntityNotFoundExceptionWhenOTPIsInvalid() {
        when(userService.findByEmail("user@example.com")).thenReturn(user);
        when(forgotPasswordRepository.findByOtpAndUser(123456, user)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            forgotPasswordService.verifyOTP(123456, "user@example.com");
        });

        assertEquals("OTP not found", exception.getMessage());

        verify(userService, times(1)).findByEmail("user@example.com");
        verify(forgotPasswordRepository, times(1)).findByOtpAndUser(123456, user);
    }

    @Test
    void verifyOTP_ShouldThrowOtpExpiredExceptionWhenOTPIsExpired() {
        forgotPassword.setExpireDate(Date.from(Instant.now().minusSeconds(70))); // OTP expirado

        when(userService.findByEmail("user@example.com")).thenReturn(user);
        when(forgotPasswordRepository.findByOtpAndUser(123456, user)).thenReturn(Optional.of(forgotPassword));

        OtpExpiredException exception = assertThrows(OtpExpiredException.class, () -> {
            forgotPasswordService.verifyOTP(123456, "user@example.com");
        });

        assertEquals("OTP expired", exception.getMessage());

        verify(userService, times(1)).findByEmail("user@example.com");
        verify(forgotPasswordRepository, times(1)).findByOtpAndUser(123456, user);
        verify(forgotPasswordRepository, times(1)).deleteById(forgotPassword.getId());
    }

    @Test
    void changePassword_ShouldUpdatePasswordWhenPasswordsMatch() {
        when(userService.findByEmail("user@example.com")).thenReturn(user);
        when(userService.save(user)).thenReturn(user);

        assertDoesNotThrow(() -> forgotPasswordService.changePassword("user@example.com", resetPasswordDto));

        verify(userService, times(1)).findByEmail("user@example.com");
        verify(userService, times(1)).save(user);
    }
    @Test
    void changePassword_ShouldThrowPasswordInvalidExceptionWhenPasswordsDoNotMatch() {
        ResetPasswordDto invalidResetPasswordDto = new ResetPasswordDto("newPassword", "differentPassword");

        PasswordInvalidException exception = assertThrows(PasswordInvalidException.class, () -> {
            forgotPasswordService.changePassword("user@example.com", invalidResetPasswordDto);
        });

        assertEquals("password and confirm password do not match", exception.getMessage());

        verify(userService, never()).findByEmail(anyString());
        verify(userService, never()).save(any(User.class));
    }
}

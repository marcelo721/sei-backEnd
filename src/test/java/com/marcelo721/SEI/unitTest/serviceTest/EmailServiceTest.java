package com.marcelo721.SEI.unitTest.serviceTest;


import com.marcelo721.SEI.entities.User;
import com.marcelo721.SEI.services.EmailService;
import com.marcelo721.SEI.utils.EmailUtils;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmailServiceTest {

    @Mock
    private JavaMailSender mailSender;

    @Mock
    private MimeMessage mimeMessage;

    @InjectMocks
    private EmailService emailService;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setName("Marcelo");
        user.setEmail("marcelo@example.com");
        user.setVerificationCode("123456");
    }

    @Test
    void sendVerifyEmail_ShouldSendEmailCorrectly() throws MessagingException, UnsupportedEncodingException {
        // Simula a criação de uma MimeMessage
        when(mailSender.createMimeMessage()).thenReturn(mimeMessage);

        // Chama o método a ser testado
        emailService.sendVerifyEmail(user);

        // Verifica se o MimeMessage foi configurado corretamente
        verify(mailSender, times(1)).createMimeMessage();
        verify(mimeMessage, times(1)).setFrom();
        verify(mimeMessage, times(1)).setRecipients(any(), anyString());
        verify(mimeMessage, times(1)).setSubject(anyString());
        verify(mimeMessage, times(1)).setText(anyString(), eq("UTF-8"), eq("html"));

        // Verifica se o e-mail foi enviado
        verify(mailSender, times(1)).send(mimeMessage);
    }

    @Test
    void sendVerifyEmail_ShouldThrowMessagingException() throws MessagingException, UnsupportedEncodingException {
        // Simula uma exceção ao criar a MimeMessage
        when(mailSender.createMimeMessage()).thenThrow(new MessagingException("Failed to create MimeMessage"));

        // Verifica se a exceção é lançada
        assertThrows(MessagingException.class, () -> {
            emailService.sendVerifyEmail(user);
        });

        // Verifica se o método createMimeMessage foi chamado
        verify(mailSender, times(1)).createMimeMessage();
    }

    @Test
    void sendVerifyEmail_ShouldThrowUnsupportedEncodingException() throws MessagingException, UnsupportedEncodingException {
        // Simula uma exceção ao configurar o MimeMessage
        when(mailSender.createMimeMessage()).thenReturn(mimeMessage);
        doThrow(new UnsupportedEncodingException("Unsupported encoding")).when(mimeMessage).setFrom();

        // Verifica se a exceção é lançada
        assertThrows(UnsupportedEncodingException.class, () -> {
            emailService.sendVerifyEmail(user);
        });

        // Verifica se o método createMimeMessage foi chamado
        verify(mailSender, times(1)).createMimeMessage();
    }
}

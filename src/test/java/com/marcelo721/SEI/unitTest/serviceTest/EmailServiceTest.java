package com.marcelo721.SEI.unitTest.serviceTest;

import com.marcelo721.SEI.entities.User;
import com.marcelo721.SEI.services.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;

import java.io.UnsupportedEncodingException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmailServiceTest {

    @Mock
    private JavaMailSender mailSender; // Mock do JavaMailSender

    @Mock
    private MimeMessage mimeMessage; // Mock do MimeMessage

    @InjectMocks
    private EmailService emailService; // Injeta os mocks no EmailService

    private User user;

    @BeforeEach
    public void setUp() {
        // Configura um usuário de teste
        user = new User();
        user.setName("Marcelo");
        user.setEmail("marcelo@example.com");
        user.setVerificationCode("123456");
    }

    @Test
    public void sendVerifyEmail_ShouldSendEmailSuccessfully() throws MessagingException, UnsupportedEncodingException {
        // Configura o comportamento do mock do JavaMailSender
        when(mailSender.createMimeMessage()).thenReturn(mimeMessage);

        // Chama o método a ser testado
        emailService.sendVerifyEmail(user);

        // Verifica se o método createMimeMessage foi chamado
        verify(mailSender, times(1)).createMimeMessage();

        // Verifica se o email foi enviado
        verify(mailSender, times(1)).send(mimeMessage);
    }

}
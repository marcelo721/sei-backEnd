package com.marcelo721.SEI.services;

import com.marcelo721.SEI.entities.User;
import com.marcelo721.SEI.utils.EmailUtils;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
public class EmailService {

    private final JavaMailSender mailSender;


    String verifyUrl = "http://localhost:8080/api/v1/users/verify?code=";

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Async
    public void sendVerifyEmail(User user) throws MessagingException, UnsupportedEncodingException {
        String toAddress = user.getEmail();
        String senderName = "Sei - Sistema de Estudos Integrado";
        String fromAddress = "marceloh.sousa023@gmail.com";
        String subject = "please verify your registration";
        String content = EmailUtils.verificationCodeView();

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        content = content.replace("[[NAME]]", user.getName());
        String verifyUrl = this.verifyUrl + user.getVerificationCode();

        content = content.replace("[[URL]]", verifyUrl);
        helper.setText(content, true);

        mailSender.send(message);
    }
}

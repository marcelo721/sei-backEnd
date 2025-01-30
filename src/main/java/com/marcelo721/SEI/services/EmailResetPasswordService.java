package com.marcelo721.SEI.services;

import com.marcelo721.SEI.services.record.MailBody;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailResetPasswordService {

    private final JavaMailSender mailSender;

    public EmailResetPasswordService(JavaMailSender mailSender){
        this.mailSender = mailSender;
    }

    public void sendMail(MailBody mailBody){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailBody.to());
        message.setFrom("marceloh.sousa@alu.ufc.br");
        message.setSubject(mailBody.subject());
        message.setText(mailBody.text());

        mailSender.send(message);
    }
}

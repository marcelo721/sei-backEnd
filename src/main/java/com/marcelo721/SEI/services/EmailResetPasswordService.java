package com.marcelo721.SEI.services;

import com.marcelo721.SEI.services.record.MailBody;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailResetPasswordService {

    private final JavaMailSender mailSender;

    public EmailResetPasswordService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendMail(MailBody mailBody) {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message, true);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        try {
            helper.setTo(mailBody.to());
            helper.setFrom("marceloh.sousa@alu.ufc.br");
            helper.setSubject(mailBody.subject());
            helper.setText(mailBody.text(), true); // 'true' enables HTML content

            mailSender.send(message);
        } catch (MessagingException | MailException e) {
            e.printStackTrace();
    }
}
}

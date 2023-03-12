package com.example.regisration.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;



@Service
@AllArgsConstructor
public class EmailService implements EmailSender{
    private final static Logger LOGGER = LoggerFactory.getLogger(EmailService.class);
    private final JavaMailSender javaMailSender;
    @Override
    @Async
    public void send(String to, String email) {
        try{
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,"utf-8");
            helper.setFrom("owenthanhha@gmail.com");//from
            helper.setTo(to);//to who
            helper.setText(email,true);//build email
            helper.setSubject("Confirm Email");//title

            javaMailSender.send(mimeMessage);
        }catch (MessagingException e){
            LOGGER.error("Failed to send email");
            throw new IllegalStateException("Failed to send email");
        }
    }
}

package com.lister.employeeonboardingsystem.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class EmailService {
    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * @param to
     * @param subject
     * @param body
     * @param uuid
     */
    //method that sends the mail
    public void sendMail(String to, String subject, String body, UUID uuid) {
        log.info("tried to send mail to" + to+"with uuid"+uuid.toString());
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }

}

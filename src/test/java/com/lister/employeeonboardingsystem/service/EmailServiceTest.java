package com.lister.employeeonboardingsystem.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.UUID;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class EmailServiceTest {
    @InjectMocks
    EmailService emailService;
    @Mock
    JavaMailSender mailSender;
    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void sendMailTest(){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("ab");
        message.setSubject("bc");
        message.setText("cd");
        emailService.sendMail("ab","bc","cd", UUID.randomUUID());
        verify(mailSender,times(1)).send(message);
    }
}

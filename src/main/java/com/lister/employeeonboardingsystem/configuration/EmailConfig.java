package com.lister.employeeonboardingsystem.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;

@Configuration
public class EmailConfig
{
    @Bean
    public SimpleMailMessage emailTemplate()
    {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("sharathrajuaddepalli@gmail.com");
        message.setFrom("sharathkumarraju1432@gmail.com");
        message.setSubject("Important email");
        message.setText("Please make the modifications !!");
        return message;
    }
}

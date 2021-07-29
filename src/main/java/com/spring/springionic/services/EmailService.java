package com.spring.springionic.services;

import javax.mail.internet.MimeMessage;

import com.spring.springionic.domain.AppOrder;

import org.springframework.mail.SimpleMailMessage;

public interface EmailService {
    void sendOrderConfirmationEmail(AppOrder obj);
    void sendEmail(SimpleMailMessage msg);
    void sendOrderConfirmationHtmlEmail(AppOrder obj);
    void sendHtmlEmail(MimeMessage msg);
}

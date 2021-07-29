package com.spring.springionic.services;

import com.spring.springionic.domain.AppOrder;

import org.springframework.mail.SimpleMailMessage;

public interface EmailService {
    void sendOrderConfirmationEmail(AppOrder obj);
    void sendEmail(SimpleMailMessage msg);
}

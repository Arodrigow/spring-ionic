package com.spring.springionic.services;

import java.util.Date;

import com.spring.springionic.domain.AppOrder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

public abstract class AbstractEmailService implements EmailService {

    @Value("${default.sender}")
    private String sender;
    
    @Override
    public void sendOrderConfirmationEmail(AppOrder obj){
        SimpleMailMessage sm = prepareSimpleMailMessegeFromOrder(obj);
        sendEmail(sm);
    }

    protected SimpleMailMessage prepareSimpleMailMessegeFromOrder(AppOrder obj) {
        
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(obj.getClient().getEmail());
        sm.setFrom(sender);
        sm.setSubject("Order confirmation - Code: "+ obj.getId());
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText(obj.toString());

        return sm;
    }
}

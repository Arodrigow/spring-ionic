package com.spring.springionic.services;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.spring.springionic.domain.AppOrder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

public abstract class AbstractEmailService implements EmailService {

    @Value("${default.sender}")
    private String sender;

    @Autowired
    private TemplateEngine tEngine;

    @Autowired
    private JavaMailSender javaMailSender;
    
    @Override
    public void sendOrderConfirmationEmail(AppOrder obj){
        SimpleMailMessage sm = prepareSimpleMailMessegeFromOrder(obj);
        sendEmail(sm);
    }

    @Override
    public void sendOrderConfirmationHtmlEmail(AppOrder obj){
        MimeMessage mm;
        try {
            mm = prepareMimeMessegeFromOrder(obj);
            sendHtmlEmail(mm);
        } catch (MessagingException e) {
            sendOrderConfirmationEmail(obj);
        }
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

    protected MimeMessage prepareMimeMessegeFromOrder(AppOrder obj) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, true);

        mmh.setTo(obj.getClient().getEmail());
        mmh.setFrom(sender);
        mmh.setSubject("Order confirmation - Code: "+ obj.getId());
        mmh.setSentDate(new Date(System.currentTimeMillis()));
        mmh.setText(htmlFromTemplateOrder(obj), true);

        return mimeMessage;
    }

    protected String htmlFromTemplateOrder(AppOrder obj){
        Context context = new Context();
        context.setVariable("order", obj);
        return tEngine.process("email/orderConfirmation", context);
    }
}

package com.spring.springionic.services;

import java.util.Calendar;
import java.util.Date;

import com.spring.springionic.domain.BilletPayment;

import org.springframework.stereotype.Service;

@Service
public class BilletService {
    public void fillBilletPayment(BilletPayment pay, Date paymentMoment){
        Calendar cal = Calendar.getInstance();
        cal.setTime(paymentMoment);
        cal.add(Calendar.DAY_OF_MONTH, 7);
        pay.setDueDate(cal.getTime());
    }
}

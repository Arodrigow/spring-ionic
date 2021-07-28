package com.spring.springionic.domain;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.spring.springionic.domain.enums.PaymentStatus;

@Entity
@JsonTypeName("creditCardPayment")
public class CreditCardPayment extends Payment{
    private static final long serialVersionUID = 1L;
    
    private Integer installmentsNumber;

    public CreditCardPayment(){}

    public CreditCardPayment(Integer id, PaymentStatus status, AppOrder order, Integer installmentsNumber) {
        super(id, status, order);
        this.installmentsNumber = installmentsNumber;
    }

    public Integer getInstallmentsNumber() {
        return installmentsNumber;
    }

    public void setInstallmentsNumber(Integer installmentsNumber) {
        this.installmentsNumber = installmentsNumber;
    }

    
}

package com.spring.springionic.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.spring.springionic.domain.enums.PaymentStatus;

@Entity
public class BilletPayment extends Payment{
    private static final long serialVersionUID = 1L;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date dueDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date paymentDate;

    public BilletPayment(){}

    public BilletPayment(Integer id, PaymentStatus status, AppOrder order, Date dueDate, Date paymentDate) {
        super(id, status, order);
        this.dueDate = dueDate;
        this.paymentDate = paymentDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    
}

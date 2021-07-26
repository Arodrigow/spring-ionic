package com.spring.springionic.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.springionic.domain.enums.PaymentStatus;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Payment implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    private Integer id;
    private Integer status;


    @OneToOne
    @JoinColumn(name = "app_order_id")
    @MapsId
    @JsonIgnore
    private AppOrder appOrder;

    public Payment(){}

    public Payment(Integer id, PaymentStatus status, AppOrder appOrder) {
        this.id = id;
        this.status = status.getCod();
        this.appOrder = appOrder;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PaymentStatus getStatus() {
        return PaymentStatus.toEnum(status);
    }

    public void setStatus(PaymentStatus status) {
        this.status = status.getCod();
    }

    @JsonIgnore
    public AppOrder getOrder() {
        return appOrder;
    }

    public void setOrder(AppOrder appOrder) {
        this.appOrder = appOrder;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Payment other = (Payment) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    
}

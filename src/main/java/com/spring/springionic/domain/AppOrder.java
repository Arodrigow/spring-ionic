package com.spring.springionic.domain;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class AppOrder implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date moment;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "appOrder")
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "delivery_id")
    private Address deliveryAddress;

    @OneToMany(mappedBy = "id.order")
    private Set<ItemOrder> items = new HashSet<>();

    public AppOrder(){}

    public AppOrder(Integer id, Date moment, Client client, Address deliveryAddress) {
        this.id = id;
        this.moment = moment;
        this.client = client;
        this.deliveryAddress = deliveryAddress;
    }

    public double getTotalValue(){
        double soma = 0.0;
        for (ItemOrder x : items){
            soma += x.getSubTotal();
        }
        return soma;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getMoment() {
        return moment;
    }

    public void setMoment(Date moment) {
        this.moment = moment;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(Address deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }
    
    public Set<ItemOrder> getItems() {
        return items;
    }

    public void setItems(Set<ItemOrder> items) {
        this.items = items;
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
        AppOrder other = (AppOrder) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {        
        
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        StringBuilder builder = new StringBuilder();

        builder.append("Order number: ");
        builder.append(getId());
        builder.append(", Moment: ");
        builder.append(sdf.format(getMoment()));
        builder.append(", Client: ");
        builder.append(getClient().getName());
        builder.append(", Payment status: ");
        builder.append(getPayment().getStatus().getDescription());
        builder.append("\nDetails\n");

        for(ItemOrder io : getItems()){
            builder.append(io.toString());
        }

        builder.append("Total value: ");
        builder.append(nf.format(getTotalValue()));

        return builder.toString();
    }

    
}

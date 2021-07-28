package com.spring.springionic.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.spring.springionic.domain.Client;

import org.hibernate.validator.constraints.Length;

public class ClientDTO implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private Integer id;
    
    @NotEmpty(message = "Must give a name.")
    @Length(min = 5, max = 120, message = "Name length must be between 5 and 80 characters.")
    private String name;

    @NotEmpty(message = "Must give a email to the category!")
    @Email(message = "Not a valid email.")
    private String email;

    public ClientDTO(){}

    public ClientDTO(Client obj) {
        this.id = obj.getId();
        this.name = obj.getName();
        this.email = obj.getEmail();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
}

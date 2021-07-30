package com.spring.springionic.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.spring.springionic.services.validation.InsertClient;

import org.hibernate.validator.constraints.Length;

@InsertClient
public class ClientNewDTO implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @NotEmpty(message = "Must give a name.")
    @Length(min = 5, max = 120, message = "Name length must be between 5 and 80 characters.")
    private String name;
    
    @NotEmpty(message = "Must give a email!")
    @Email(message = "Not a valid email.")
    private String email;

    @NotEmpty(message = "Must give a password !")
    private String password;

    @NotEmpty(message = "Must give a cpf!")
    private String cpfOrCnpj;

    private Integer type;

    @NotEmpty(message = "Must be filled!")
    private String publicPlace;

    @NotEmpty(message = "Must be filled!")
    private String number;

    private String complement;

    private String district;

    @NotEmpty(message = "Must be filled!")
    private String cep;

    @NotEmpty(message = "Must be filled!")
    private String phone1;
    private String phone2;
    private String phone3;

    private Integer cityId;

    public ClientNewDTO(){}

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCpfOrCnpj() {
        return cpfOrCnpj;
    }

    public void setCpfOrCnpj(String cpfOrCnpj) {
        this.cpfOrCnpj = cpfOrCnpj;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPublicPlace() {
        return publicPlace;
    }

    public void setPublicPlace(String publicPlace) {
        this.publicPlace = publicPlace;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getPhone3() {
        return phone3;
    }

    public void setPhone3(String phone3) {
        this.phone3 = phone3;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }    
}

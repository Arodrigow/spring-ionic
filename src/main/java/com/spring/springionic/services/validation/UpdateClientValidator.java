package com.spring.springionic.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.spring.springionic.domain.Client;
import com.spring.springionic.dto.ClientDTO;
import com.spring.springionic.repositories.ClientRepository;
import com.spring.springionic.resources.exceptions.FieldMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

public class UpdateClientValidator implements ConstraintValidator<UpdateClient, ClientDTO> {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ClientRepository repo;

    @Override
    public void initialize(UpdateClient ann) {
    }

    @Override
    public boolean isValid(ClientDTO objDto, ConstraintValidatorContext context) {

        Map<String, String> map =(Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Integer uriId = Integer.parseInt(map.get("id"));

        List<FieldMessage> list = new ArrayList<>();
    
        Client aux = repo.findByEmail(objDto.getEmail());
        
        if(aux != null && !aux.getId().equals(uriId)){
            list.add(new FieldMessage("email", "Email already in use."));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();

            context.buildConstraintViolationWithTemplate(e.getMsg())
                .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }

        return list.isEmpty();

    }
}
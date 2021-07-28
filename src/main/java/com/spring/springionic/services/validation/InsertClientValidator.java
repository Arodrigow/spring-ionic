package com.spring.springionic.services.validation;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.spring.springionic.domain.enums.ClientType;
import com.spring.springionic.dto.ClientNewDTO;
import com.spring.springionic.resources.exceptions.FieldMessage;
import com.spring.springionic.services.validation.utils.BR;

public class InsertClientValidator implements ConstraintValidator<InsertClient, ClientNewDTO> {

@Override
public void initialize(InsertClient ann) {
}

@Override
public boolean isValid(ClientNewDTO objDto, ConstraintValidatorContext context) {

    List<FieldMessage> list = new ArrayList<>();
    
    if(objDto.getType().equals(ClientType.NATURALPERSON.getCod()) && !BR.isValidSsn(objDto.getCpfOrCnpj())){
        list.add(new FieldMessage("cpfOrCnpj", "Invalid CPF."));
    }    
    if(objDto.getType().equals(ClientType.LEGALPERSON.getCod()) && !BR.isValidTfn(objDto.getCpfOrCnpj())){
        list.add(new FieldMessage("cpfOrCnpj", "Invalid CNPJ."));
    }
    

    for (FieldMessage e : list) {
        context.disableDefaultConstraintViolation();

        context.buildConstraintViolationWithTemplate(e.getMsg())
            .addPropertyNode(e.getFieldName()).addConstraintViolation();
    }

    return list.isEmpty();

}
}
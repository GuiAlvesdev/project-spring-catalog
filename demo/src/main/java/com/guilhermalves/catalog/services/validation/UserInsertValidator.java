package com.guilhermalves.catalog.services.validation;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import com.guilhermalves.catalog.dto.UserInsertDTO;
import com.guilhermalves.catalog.entities.User;
import com.guilhermalves.catalog.repositories.UserRepository;
import com.guilhermalves.catalog.resources.exceptions.FieldMessage;
import org.springframework.beans.factory.annotation.Autowired;

public class UserInsertValidator implements ConstraintValidator<UserInsertValid, UserInsertDTO> {

    @Autowired
    private UserRepository repository;



    @Override
    public void initialize(UserInsertValid ann) {
    }


    @Override
    public boolean isValid(UserInsertDTO dto, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();

        User user = repository.findByEmail(dto.getEmail());
        if (user != null){
            list.add(new FieldMessage("email", "email ja cadastrado"));

        }



        // Coloque aqui seus testes de validação, acrescentando objetos FieldMessage à lista

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }




}

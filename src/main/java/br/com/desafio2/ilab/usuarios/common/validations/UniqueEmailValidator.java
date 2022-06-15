package br.com.desafio2.ilab.usuarios.common.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.desafio2.ilab.usuarios.models.User;
import br.com.desafio2.ilab.usuarios.repositories.UserRepository;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        User inDB = userRepository.findByEmail(value).orElse(null);

        if (inDB == null) {
            return true;
        }

        return false;
    }

}
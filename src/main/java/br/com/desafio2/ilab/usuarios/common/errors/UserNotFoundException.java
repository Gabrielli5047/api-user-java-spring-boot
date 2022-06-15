package br.com.desafio2.ilab.usuarios.common.errors;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends NoSuchElementException {
    public UserNotFoundException() {
        super("Usuário não encontrado.");
    }
}

package br.com.desafio2.ilab.usuarios.common.errors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ValidationErrors {
    private String message;
    private String field;
}

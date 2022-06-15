package br.com.desafio2.ilab.usuarios.DTO;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import lombok.Data;

@Data
public class UserUpdateDTO {

    private Integer id;

    @Size(max = 50, message = "Limite de 50 caracteres.")
    @NotBlank(message = "O campo nome é obrigatório")
    private String name;

    @NotBlank(message = "O campo cpf é obrigatório")
    @CPF(message = "CPF inválido")
    private String cpf;

    @NotBlank(message = "O campo email é obrigatório")
    @Email(message = "Email inválido")
    private String email;

    private String phone;

    private LocalDate birthdate; // format 2022-06-02

}

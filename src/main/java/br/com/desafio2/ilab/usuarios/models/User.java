package br.com.desafio2.ilab.usuarios.models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import br.com.desafio2.ilab.usuarios.common.validations.UniqueCPF;
import br.com.desafio2.ilab.usuarios.common.validations.UniqueEmail;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false, length = 50)
	@Size(max = 50, message = "Limite de 50 caracteres.")
	@NotBlank(message = "O campo nome é obrigatório")
	private String name;

	@Column(nullable = false, length = 11, unique = true)
	@NotBlank(message = "O campo cpf é obrigatório")
	@CPF(message = "CPF inválido")
	@UniqueCPF
	private String cpf;

	@Column(nullable = false, length = 100, unique = true)
	@NotBlank(message = "O campo email é obrigatório")
	@Email(message = "Email inválido")
	@UniqueEmail
	private String email;

	private String phone;

	private LocalDate birthdate; // format 2022-06-02

}

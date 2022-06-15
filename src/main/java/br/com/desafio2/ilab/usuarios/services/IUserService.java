package br.com.desafio2.ilab.usuarios.services;

import javax.servlet.http.HttpServletRequest;

import br.com.desafio2.ilab.usuarios.DTO.UserPaginationDTO;
import br.com.desafio2.ilab.usuarios.DTO.UserUpdateDTO;
import br.com.desafio2.ilab.usuarios.models.User;

public interface IUserService {

    UserPaginationDTO list(Integer page, Integer limit);

    User get(Integer id);

    User save(User user);

    User update(UserUpdateDTO user, Integer id);

    void delete(Integer id, HttpServletRequest request);
}

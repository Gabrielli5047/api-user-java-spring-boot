package br.com.desafio2.ilab.usuarios.services;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.desafio2.ilab.usuarios.DTO.UserPaginationDTO;
import br.com.desafio2.ilab.usuarios.DTO.UserUpdateDTO;
import br.com.desafio2.ilab.usuarios.common.errors.UserNotFoundException;
import br.com.desafio2.ilab.usuarios.models.User;
import br.com.desafio2.ilab.usuarios.providers.OrderApiProvider;
import br.com.desafio2.ilab.usuarios.repositories.UserRepository;

@Service
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserPaginationDTO list(Integer page, Integer limit) {
        if (page == null)
            page = 0;
        if (limit == null)
            limit = 20;

        Pageable pagination = PageRequest.of(page, limit);

        UserPaginationDTO paginationDTO = new UserPaginationDTO(userRepository.findAll(pagination));

        return paginationDTO;
    }

    @Override
    public User get(Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException());

        return user;
    }

    @Override
    public User save(User user) {
        user.setCpf(user.getCpf().replace(".", ""));
        user.setCpf(user.getCpf().replace("-", ""));

        return this.userRepository.save(user);
    }

    @Override
    public User update(UserUpdateDTO userUpdateDTO, Integer id) {
        userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException());

        User emailExists = userRepository.findByEmail(userUpdateDTO.getEmail()).orElse(null);
        if (emailExists != null && emailExists.getId() != id) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email indisponível");
        }

        userUpdateDTO.setCpf(userUpdateDTO.getCpf().replace(".", ""));
        userUpdateDTO.setCpf(userUpdateDTO.getCpf().replace("-", ""));
        User cpfExists = userRepository.findByCpf(userUpdateDTO.getCpf()).orElse(null);
        if (cpfExists != null && cpfExists.getId() != id) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CPF indisponível");
        }

        User user = new User();
        user.setId(id);
        user.setName(userUpdateDTO.getName());
        user.setEmail(userUpdateDTO.getEmail());
        user.setCpf(userUpdateDTO.getCpf());
        user.setPhone(userUpdateDTO.getPhone());
        user.setBirthdate(userUpdateDTO.getBirthdate());

        return userRepository.save(user);
    }

    @Override
    public void delete(Integer id, HttpServletRequest request) {
        try {
            String token = request.getHeader("Authorization");
            Boolean canDeleteUserById = OrderApiProvider.canDeleteUser(id, token);

            if (canDeleteUserById) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Não foi possível excluir o usuário pois ele possui pedidos cadastrado.");
            }

            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new UserNotFoundException();
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(e.getStatus(), e.getReason());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

}

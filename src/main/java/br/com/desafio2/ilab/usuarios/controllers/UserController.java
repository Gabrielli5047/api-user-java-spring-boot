package br.com.desafio2.ilab.usuarios.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafio2.ilab.usuarios.DTO.UserPaginationDTO;
import br.com.desafio2.ilab.usuarios.DTO.UserUpdateDTO;
import br.com.desafio2.ilab.usuarios.models.User;
import br.com.desafio2.ilab.usuarios.services.UserService;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public UserPaginationDTO list(@RequestParam(name = "page", required = false) Integer page,
            @RequestParam(name = "limit", required = false) Integer limit) {
        return userService.list(page, limit);
    }

    @GetMapping("/{id}")
    public User get(@PathVariable Integer id) {
        return userService.get(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User save(@Valid @RequestBody User user) {
        return userService.save(user);
    }

    @PutMapping("/{id}")
    public User update(@Valid @RequestBody UserUpdateDTO user, @PathVariable Integer id) {
        return userService.update(user, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id, HttpServletRequest request) {
        userService.delete(id, request);
    }

}

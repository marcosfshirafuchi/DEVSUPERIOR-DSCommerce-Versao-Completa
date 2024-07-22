package com.devsuperior.dscommerce.controllers;

import com.devsuperior.dscommerce.dto.UserDTO;
import com.devsuperior.dscommerce.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

//Essa anotation vai configurar quando a aplicação for executada, vai responder pela web
@RestController
//Vai configurar a rota
@RequestMapping(value = "/users")
public class UserController {

    //Colocar o Autowired para injetar
    @Autowired
    //Injetar um componente do ProductService
    private UserService service;

    //Método Get
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_CLIENT')")
    @GetMapping(value = "/me")
    public ResponseEntity<UserDTO>  getMe(){
        UserDTO dto = service.getMe();
        return ResponseEntity.ok(dto);
    }



}

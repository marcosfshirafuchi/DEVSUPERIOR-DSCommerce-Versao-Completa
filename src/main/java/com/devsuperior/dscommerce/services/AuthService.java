package com.devsuperior.dscommerce.services;

import com.devsuperior.dscommerce.entities.User;
import com.devsuperior.dscommerce.services.exceptions.ForbiddenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <h1> DEVSUPERIOR - Java Spring Professional - Capítulo: Login e controle de acesso</h1>
 * DESAFIO: Projeto Spring Boot estruturado
 * <p>
 * <b>Note:</b> Desenvolvido na linguagem Java.
 *
 * @author  Marcos Ferreira Shirafuchi
 * @version 1.0
 * @since   22/07/2024
 */

@Service
public class AuthService {
    @Autowired
    private UserService userService;

    public void validateSelfOrAdmin(long userId){
        User me = userService.authenticated();
        if(!me.hasRole("ROLE_ADMIN")&& !me.getId().equals(userId)){
            throw new ForbiddenException("Access denied");
        }
    }
}

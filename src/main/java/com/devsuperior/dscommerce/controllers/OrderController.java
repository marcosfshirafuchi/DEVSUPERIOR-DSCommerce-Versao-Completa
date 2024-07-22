package com.devsuperior.dscommerce.controllers;

import com.devsuperior.dscommerce.dto.OrderDTO;
import com.devsuperior.dscommerce.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;




//Essa anotation vai configurar quando a aplicação for executada, vai responder pela web
@RestController
//Vai configurar a rota
@RequestMapping(value = "/orders")
public class OrderController {

    //Colocar o Autowired para injetar
    @Autowired
    //Injetar um componente do ProductService
    private OrderService service;

    //Método Get
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<OrderDTO>  findById(@PathVariable Long id){
        OrderDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }



}

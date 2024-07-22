package com.devsuperior.dscommerce.controllers;

import com.devsuperior.dscommerce.dto.CategoryDTO;
import com.devsuperior.dscommerce.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Essa anotation vai configurar quando a aplicação for executada, vai responder pela web
@RestController
//Vai configurar a rota
@RequestMapping(value = "/categories")
public class CategoryController {

    //Colocar o Autowired para injetar
    @Autowired
    //Injetar um componente do ProductService
    private CategoryService service;

    //Método Get
    //Pageable faz a busca pagina dos registros
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> findAll(){
        List<CategoryDTO> list = service.findAll();
        return ResponseEntity.ok(list);
    }

}

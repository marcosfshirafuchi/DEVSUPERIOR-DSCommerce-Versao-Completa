package com.devsuperior.dscommerce.controllers;

import com.devsuperior.dscommerce.dto.ProductDTO;
import com.devsuperior.dscommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

//Essa anotation vai configurar quando a aplicação for executada, vai responder pela web
@RestController
//Vai configurar a rota
@RequestMapping(value = "/products")
public class ProductController {

    //Colocar o Autowired para injetar
    @Autowired
    //Injetar um componente do ProductService
    private ProductService service;

    //Método Get
    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDTO>  findById(@PathVariable Long id){
        ProductDTO dto = service.findById(id);
        //Estamos custamizando a respota
        return ResponseEntity.ok(dto);
    }

    //Método Get
    //Pageable faz a busca pagina dos registros
    @GetMapping
    public ResponseEntity<Page<ProductDTO>> findAll(Pageable pageable){
        Page<ProductDTO> dto = service.findAll(pageable);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    //RequestBody vai receber os dados da Request do postman
    public ResponseEntity<ProductDTO>  insert(@RequestBody ProductDTO dto){
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    //Método Get
    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductDTO>  update(@PathVariable Long id,@RequestBody ProductDTO dto){
        dto = service.update(id,dto);
        //Estamos custamizando a respota
        return ResponseEntity.ok(dto);
    }

}

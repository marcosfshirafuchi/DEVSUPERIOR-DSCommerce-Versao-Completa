package com.devsuperior.dscommerce.services;


import com.devsuperior.dscommerce.dto.ProductDTO;
import com.devsuperior.dscommerce.entities.Product;
import com.devsuperior.dscommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//Pode colocar @Component ou service
@Service
public class
ProductService {

    //Dependencia do repositoy
    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id){
        //Esse findById faz a consulta SQL no banco de dados
        Product product = repository.findById(id).get();
        return new ProductDTO(product);
    }

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable){
        Page<Product> result = repository.findAll(pageable);
        //Converte lista de Product para lista ProductDTO
        //Converte cada registro da Lista Product(x) em um registro da Lista ProductDTO
        return result.map(x -> new ProductDTO(x));
    }

    @Transactional
    public ProductDTO insert(ProductDTO dto){
        Product entity = new Product();
        copyDTOToEntity(dto, entity);
        entity = repository.save(entity);
        return new ProductDTO(entity);
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO dto){
        //Vai estanciar pelo id do banco de dados
        Product entity = repository.getReferenceById(id);
        copyDTOToEntity(dto, entity);
        return new ProductDTO(entity);
    }

    @Transactional
    public void delete(Long id){
        //Esse findById faz a consulta SQL no banco de dados
        repository.deleteById(id);
    }

    private void copyDTOToEntity(ProductDTO dto, Product entity) {
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setImgUrl(dto.getImgUrl());
    }


}

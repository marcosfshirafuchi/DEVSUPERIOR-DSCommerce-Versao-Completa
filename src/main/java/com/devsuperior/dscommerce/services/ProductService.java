package com.devsuperior.dscommerce.services;


import com.devsuperior.dscommerce.dto.CategoryDTO;
import com.devsuperior.dscommerce.dto.ProductDTO;
import com.devsuperior.dscommerce.dto.ProductMinDTO;
import com.devsuperior.dscommerce.entities.Category;
import com.devsuperior.dscommerce.entities.Product;
import com.devsuperior.dscommerce.repositories.ProductRepository;
import com.devsuperior.dscommerce.services.exceptions.DatabaseException;
import com.devsuperior.dscommerce.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
public class
ProductService {

    //Dependencia do repositoy
    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id){
            //Esse findById faz a consulta SQL no banco de dados

            Product product = repository.findById(id).orElseThrow(
                    //Aqui você está lançando a própria exceção
                    ()-> new ResourceNotFoundException("Recurso não encontrado"));
            return new ProductDTO(product);

    }

    @Transactional(readOnly = true)
    public Page<ProductMinDTO> findAll(String name, Pageable pageable){
        Page<Product> result = repository.searchByName(name,pageable);
        //Converte lista de Product para lista ProductDTO
        //Converte cada registro da Lista Product(x) em um registro da Lista ProductDTO
        return result.map(x -> new ProductMinDTO(x));
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
        try{
            //Vai estanciar pelo id do banco de dados
            Product entity = repository.getReferenceById(id);
            copyDTOToEntity(dto, entity);
            entity = repository.save(entity);
            return new ProductDTO(entity);
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Recurso não encontrado");
        }

    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id){
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        try {
            //Esse findById faz a consulta SQL no banco de dados
            repository.deleteById(id);
        }catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }

    private void copyDTOToEntity(ProductDTO dto, Product entity) {
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setImgUrl(dto.getImgUrl());
        //Limpar as categories
        entity.getCategories().clear();
        //Adicionar as novas categorias
        for (CategoryDTO catDTO : dto.getCategories()){
            Category cat = new Category();
            cat.setId(catDTO.getId());
            //Inserindo as categories
            entity.getCategories().add(cat);
        }
    }


}

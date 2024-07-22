package com.devsuperior.dscommerce.services;

import com.devsuperior.dscommerce.dto.OrderDTO;
import com.devsuperior.dscommerce.entities.Order;
import com.devsuperior.dscommerce.repositories.OrderRepository;
import com.devsuperior.dscommerce.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {
    //Dependencia do repositoy
    @Autowired
    private OrderRepository repository;

    @Transactional(readOnly = true)
    public OrderDTO findById(Long id){
        //Esse findById faz a consulta SQL no banco de dados

        Order order = repository.findById(id).orElseThrow(
                //Aqui você está lançando a própria exceção
                ()-> new ResourceNotFoundException("Recurso não encontrado"));
        return new OrderDTO(order);

    }
}

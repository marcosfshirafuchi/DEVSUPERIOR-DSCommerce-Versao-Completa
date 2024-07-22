package com.devsuperior.dscommerce.dto;
import com.devsuperior.dscommerce.entities.Product;

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

public class ProductMinDTO {
    private Long id;
    private String name;
    private Double price;
    private String imgUrl;


    public ProductMinDTO(Long id, String name, Double price, String imgUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    public ProductMinDTO(Product entity) {
        id = entity.getId();
        name = entity.getName();
        price = entity.getPrice();
        imgUrl = entity.getImgUrl();
    }

    //O DTO não altera, só tem os getters

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public String getImgUrl() {
        return imgUrl;
    }
}

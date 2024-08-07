package com.devsuperior.dscommerce.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

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

@Entity
//Vai customizar o nome da tabela do banco de dados
@Table(name = "tb_product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    //Para colocar um texto maior, coloque column definition
    @Column(columnDefinition = "TEXT")
    private String description;
    private Double price;
    private String imgUrl;

    //Relação muitos para muitos
    @ManyToMany
    //Vai juntar a tabela product_category
    @JoinTable(name = "tb_product_category",
            //Vai colocar o id da tabela Product na tabela product_category
            joinColumns = @JoinColumn(name = "product_id"),
            //Vai colocar o id da tabela Category na tabela product_category
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    //Quando não tem repetição, usar Set e não list
    private Set<Category> categories = new HashSet<>();

    //Quando não tem repetição, usar Set e não list
    //Id da classe OrderItem e order da classe OrderItemPK
    @OneToMany(mappedBy = "id.product")
    private Set<OrderItem> items = new HashSet<>();
    public Product(){

    }

    public Product(Long id, String name, String description, Double price, String imgUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public Set<OrderItem> getItems() {
        return items;
    }

    public List<Order> getOrders(){
        return items.stream().map(x-> x.getOrder()).toList();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}

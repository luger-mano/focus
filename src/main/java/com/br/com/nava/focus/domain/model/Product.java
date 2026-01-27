package com.br.com.nava.focus.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "TABLE_PRODUCTS")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID productId;
    private String name;
    private Double price;
    private String description;
    private Boolean available;
    private Integer amount;


    public Product(UUID productId, String name, Double price, String description, Boolean available, Integer amount) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.description = description;
        this.available = available;
        this.amount = amount;
    }

    public Product() {
    }

}
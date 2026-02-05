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
    private UUID productId;
    private String name;

    public Product() {
    }

}
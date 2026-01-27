package com.br.com.nava.focus.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "TABLE_STORES")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID storeId;
    private String name;
    @Embedded
    private Contact contact;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_address_id")
    private Address address;
    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Product> products;


    public Store(UUID storeId, String name, Contact contact, Address address, List<Product> products) {
        this.storeId = storeId;
        this.name = name;
        this.contact = contact;
        this.address = address;
        this.products = products;
    }

    public Store() {
    }

}

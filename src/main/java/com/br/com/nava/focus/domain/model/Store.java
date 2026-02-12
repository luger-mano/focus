package com.br.com.nava.focus.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Column(name = "store_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID storeId;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Embedded
    private Contact contact;

    @OneToOne(mappedBy = "store", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(mappedBy = "store")
    private List<User> users;

    @OneToMany(mappedBy = "store")
    private List<StoreProduct> storeProducts;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    @JsonIgnore
    private Brand brand;

    public Store(UUID storeId, String name, Contact contact, Address address, List<User> users, List<StoreProduct> storeProducts, Brand brand) {
        this.storeId = storeId;
        this.name = name;
        this.contact = contact;
        this.address = address;
        this.users = users;
        this.storeProducts = storeProducts;
        this.brand = brand;
    }

    public Store() {
    }

}

package com.br.com.nava.focus.domain.model;


import jakarta.persistence.*;

@Entity
@Table(name = "TABLE_STORES_PRODUCTS")
public class StoreProduct {

    @EmbeddedId
    private StoreProductId id;

    @ManyToOne
    @MapsId("storeId")
    @JoinColumn(name = "store_id")
    private Store store;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "price")
    private Double price;

    @Column(name = "description")
    private String description;

    @Column(name = "available")
    private Boolean available;

    @Column(name = "amount")
    private Integer amount;

    public StoreProduct(StoreProductId id, Store store, Product product, Double price, String description, Boolean available, Integer amount) {
        this.id = id;
        this.store = store;
        this.product = product;
        this.price = price;
        this.description = description;
        this.available = available;
        this.amount = amount;
    }

    public StoreProduct() {
    }
}

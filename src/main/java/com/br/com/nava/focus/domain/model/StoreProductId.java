package com.br.com.nava.focus.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public class StoreProductId {

    @Column(name = "store_id")
    private UUID storeId;

    @Column(name = "product_id")
    private UUID productId;


    public StoreProductId(UUID storeId, UUID productId) {
        this.storeId = storeId;
        this.productId = productId;
    }

    public StoreProductId() {
    }

}

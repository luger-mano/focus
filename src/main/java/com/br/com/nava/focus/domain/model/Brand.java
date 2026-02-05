package com.br.com.nava.focus.domain.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "TABLE_BRANDS")
public class Brand {

    @Id
    @Column(name = "brandId", unique = true, nullable = false)
    private String brandId;
    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL)
    private List<Store> stores;

    public Brand(String brandId, List<Store> stores) {
        this.brandId = brandId;
        this.stores = stores;
    }

    public Brand() {

    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public List<Store> getStores() {
        return stores;
    }

    public void setStores(List<Store> stores) {
        this.stores = stores;
    }
}

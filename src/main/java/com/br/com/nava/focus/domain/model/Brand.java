package com.br.com.nava.focus.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "TABLE_BRANDS")
public class Brand {

    @Id
    @Column(name = "brand_id", unique = true, nullable = false)
    private String brandId;
    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Store> stores;

    public Brand(String brandId, List<Store> stores) {
        this.brandId = brandId;
        this.stores = stores;
    }

    public Brand() {

    }

}

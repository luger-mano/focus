package com.br.com.nava.focus.adapter.dto.store;

import com.br.com.nava.focus.domain.model.Brand;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoresBrandResponseDto {
    private Brand brand;

    public StoresBrandResponseDto(Brand brand) {
        this.brand = brand;
    }

}

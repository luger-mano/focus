package com.br.com.nava.focus.domain.repository;

import com.br.com.nava.focus.adapter.dto.StoreResponseDto;
import com.br.com.nava.focus.domain.model.Store;

public interface StoreMapper {
    static StoreResponseDto toDto(Store store) {
        return new StoreResponseDto(store.getName(), store.getContact(), store.getAddress(), store.getProducts());
    }
}

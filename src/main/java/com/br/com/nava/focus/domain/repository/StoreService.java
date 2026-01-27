package com.br.com.nava.focus.domain.repository;

import com.br.com.nava.focus.adapter.dto.CreateStoreRequestDto;
import com.br.com.nava.focus.adapter.dto.StoreResponseDto;
import com.br.com.nava.focus.domain.model.Store;

import java.util.List;

public interface StoreService {

    Store createStore(CreateStoreRequestDto requestDto);
    List<StoreResponseDto> getAllStores();
}

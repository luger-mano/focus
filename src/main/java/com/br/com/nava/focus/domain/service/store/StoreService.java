package com.br.com.nava.focus.domain.service.store;

import com.br.com.nava.focus.adapter.dto.store.CreateStoreRequestDto;
import com.br.com.nava.focus.adapter.dto.store.StorePaginationDto;
import com.br.com.nava.focus.adapter.dto.store.StoreResponseDto;
import com.br.com.nava.focus.domain.model.Store;

import java.util.List;
import java.util.UUID;

public interface StoreService {

    StoreResponseDto createStore(CreateStoreRequestDto requestDto, String brandId);

    List<StorePaginationDto> getAllStores(int page, int pageSize);

    StoreResponseDto getStoreById(UUID id);

}

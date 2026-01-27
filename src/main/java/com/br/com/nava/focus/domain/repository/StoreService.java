package com.br.com.nava.focus.domain.repository;

import com.br.com.nava.focus.adapter.dto.store.CreateStoreRequestDto;
import com.br.com.nava.focus.adapter.dto.store.StorePaginationDto;
import com.br.com.nava.focus.adapter.dto.store.StoreResponseDto;
import com.br.com.nava.focus.domain.model.Store;

import java.util.List;

public interface StoreService {

    Store createStore(CreateStoreRequestDto requestDto);

    List<StorePaginationDto> getAllStores(int page, int pageSize);

}

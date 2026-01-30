package com.br.com.nava.focus.mapper;


import com.br.com.nava.focus.adapter.dto.store.StoreResponseDto;
import com.br.com.nava.focus.domain.model.Store;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StoreMapper {
    StoreResponseDto toDto(Store store);
}

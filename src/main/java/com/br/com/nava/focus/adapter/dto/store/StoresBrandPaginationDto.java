package com.br.com.nava.focus.adapter.dto.store;

public record StoresBrandPaginationDto(StoresBrandResponseDto dto,
                                       int page,
                                       int pageSize,
                                       int totalPages,
                                       int totalElements){

}

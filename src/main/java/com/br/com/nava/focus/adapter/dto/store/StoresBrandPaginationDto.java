package com.br.com.nava.focus.adapter.dto.store;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoresBrandPaginationDto {

    private StoresBrandResponseDto dto;
    private int page;
    private int pageSize;
    private int totalPages;
    private int totalElements;

    public StoresBrandPaginationDto(StoresBrandResponseDto dto, int page, int pageSize, int totalPages, int totalElements) {
        this.dto = dto;
        this.page = page;
        this.pageSize = pageSize;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
    }
}

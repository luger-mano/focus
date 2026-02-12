package com.br.com.nava.focus.adapter.dto.store;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StorePaginationDto {
    private StoreResponseDto dto;
    private int page;
    private int pageSize;
    private int totalPages;
    private int totalElements;

    public StorePaginationDto(StoreResponseDto dto, int page, int pageSize, int totalPages, int totalElements) {
        this.dto = dto;
        this.page = page;
        this.pageSize = pageSize;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
    }
}

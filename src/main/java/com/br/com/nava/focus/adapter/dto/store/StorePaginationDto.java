package com.br.com.nava.focus.adapter.dto.store;

public record StorePaginationDto (StoreResponseDto dto,
                                  int page,
                                  int pageSize,
                                  int totalPages,
                                  int totalElements) {
}

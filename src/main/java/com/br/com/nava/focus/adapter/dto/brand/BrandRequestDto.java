package com.br.com.nava.focus.adapter.dto.brand;

import com.br.com.nava.focus.domain.model.Store;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record BrandRequestDto(@NotBlank(message = "Campo brandId não pode estar vazio") String brandId,
                              List<Store> stores)  {
}

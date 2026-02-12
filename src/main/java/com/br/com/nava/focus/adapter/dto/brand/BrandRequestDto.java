package com.br.com.nava.focus.adapter.dto.brand;

import com.br.com.nava.focus.domain.model.Store;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BrandRequestDto {

    @NotBlank(message = "Campo brandId não pode estar vazio")
    private String brandId;
    private List<Store> stores;
}

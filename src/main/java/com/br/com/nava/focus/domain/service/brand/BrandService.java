package com.br.com.nava.focus.domain.service.brand;

import com.br.com.nava.focus.adapter.dto.brand.BrandRequestDto;
import com.br.com.nava.focus.adapter.dto.brand.BrandResponseDto;
import com.br.com.nava.focus.domain.model.Brand;

public interface BrandService {
    BrandResponseDto createBrand(BrandRequestDto dto);

    BrandResponseDto getBrandById(String brandId);
    Brand findById(String brandId);
}

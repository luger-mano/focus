package com.br.com.nava.focus.mapper;

import com.br.com.nava.focus.adapter.dto.brand.BrandRequestDto;
import com.br.com.nava.focus.adapter.dto.brand.BrandResponseDto;
import com.br.com.nava.focus.domain.model.Brand;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BrandMapper {

    Brand brandRequestToEntity(BrandRequestDto dto);
    Brand brandResponseToEntity(BrandResponseDto dto);

    BrandResponseDto brandEntityToBrandResponseDto(Brand brand);
    BrandRequestDto brandEntityToBrandRequestDto(Brand brand);


}

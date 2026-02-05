package com.br.com.nava.focus.domain.service.brand;


import com.br.com.nava.focus.adapter.dto.brand.BrandRequestDto;
import com.br.com.nava.focus.adapter.dto.brand.BrandResponseDto;
import com.br.com.nava.focus.domain.repository.BrandRepository;
import com.br.com.nava.focus.mapper.BrandMapper;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final BrandMapper mappper;

    public BrandServiceImpl(BrandRepository brandRepository, BrandMapper mappper) {
        this.brandRepository = brandRepository;
        this.mappper = mappper;
    }

    @Override
    @Transactional
    public BrandResponseDto createBrand(BrandRequestDto dto) {
        try{
            var brandDto = this.mappper.brandRequestToEntity(dto);

            var brandMapper = brandRepository.save(brandDto);

            return this.mappper.brandEntityToBrandResponseDto(brandMapper);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public BrandResponseDto getBrandById(String brandId) {
        var brandOpp = brandRepository.findByBrandId(brandId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return this.mappper.brandEntityToBrandResponseDto(brandOpp);

    }
}

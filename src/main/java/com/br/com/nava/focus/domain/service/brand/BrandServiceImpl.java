package com.br.com.nava.focus.domain.service.brand;


import com.br.com.nava.focus.adapter.dto.brand.BrandRequestDto;
import com.br.com.nava.focus.adapter.dto.brand.BrandResponseDto;
import com.br.com.nava.focus.domain.model.Brand;
import com.br.com.nava.focus.domain.repository.BrandRepository;
import com.br.com.nava.focus.mapper.BrandMapper;
import jakarta.transaction.Transactional;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;

    public BrandServiceImpl(BrandRepository brandRepository, BrandMapper mapper) {
        this.brandRepository = brandRepository;
        this.brandMapper = mapper;
    }

    @Override
    @Transactional
    public BrandResponseDto createBrand(BrandRequestDto dto) {
        try{
            var brandDto = this.brandMapper.brandRequestToEntity(dto);

            var brandMapper = brandRepository.save(brandDto);

            return this.brandMapper.brandEntityToBrandResponseDto(brandMapper);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Cacheable(cacheNames = "brand", key = "#brandId")
    public BrandResponseDto getBrandById(String brandId) {
        var brandOpp = brandRepository.findByBrandId(brandId)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return this.brandMapper.brandEntityToBrandResponseDto(brandOpp);

    }

    @Override
    @Cacheable(cacheNames = "brand", key = "#brandId")
    public Brand findById(String brandId) {
        return brandRepository.findById(brandId)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }




}

package com.br.com.nava.focus.adapter.controller;


import com.br.com.nava.focus.adapter.dto.brand.BrandRequestDto;
import com.br.com.nava.focus.adapter.dto.brand.BrandResponseDto;
import com.br.com.nava.focus.domain.service.brand.BrandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class BrandController {

    private final BrandService brandService;

    @PostMapping("/brand")
    public ResponseEntity<BrandResponseDto> createBrand(@RequestBody @Valid BrandRequestDto dto) {
        var brand = brandService.createBrand(dto);
        return ResponseEntity.ok(brand);
    }

    @GetMapping("/{brandId}/brands")
    public ResponseEntity<BrandResponseDto> getAllBrandsById(@PathVariable String brandId){
        var brand = brandService.getBrandById(brandId);
        return ResponseEntity.ok(brand);
    }

}

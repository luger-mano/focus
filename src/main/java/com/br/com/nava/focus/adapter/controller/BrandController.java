package com.br.com.nava.focus.adapter.controller;


import com.br.com.nava.focus.adapter.dto.brand.BrandRequestDto;
import com.br.com.nava.focus.adapter.dto.brand.BrandResponseDto;
import com.br.com.nava.focus.domain.service.brand.BrandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class BrandController {

    private final BrandService brandService;

    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @PostMapping("/admin/brand")
    public ResponseEntity<BrandResponseDto> createBrand(@RequestBody @Valid BrandRequestDto dto) {
        var brand = brandService.createBrand(dto);
        return ResponseEntity.ok(brand);
    }

    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @GetMapping("/admin/{brandId}/brands")
    public ResponseEntity<BrandResponseDto> getAllBrandsById(@PathVariable String brandId){
        var brand = brandService.getBrandById(brandId);
        return ResponseEntity.ok(brand);
    }

}

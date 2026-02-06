package com.br.com.nava.focus.adapter.controller;


import com.br.com.nava.focus.adapter.dto.store.CreateStoreRequestDto;
import com.br.com.nava.focus.adapter.dto.store.StorePaginationDto;
import com.br.com.nava.focus.adapter.dto.store.StoreResponseDto;
import com.br.com.nava.focus.adapter.dto.store.StoresBrandPaginationDto;
import com.br.com.nava.focus.domain.service.store.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class StoreController {

    private final StoreService storeService;

    @PostMapping("/brand/{brandId}/store")
    public ResponseEntity<StoreResponseDto> createStore(@RequestBody CreateStoreRequestDto requestDto, @PathVariable String brandId) {
        var storeCreated = storeService.createStore(requestDto, brandId);
        return ResponseEntity.ok(storeCreated);
    }

    @GetMapping("/stores")
    public ResponseEntity<List<StorePaginationDto>> getAllStores(@RequestParam(value = "page",
                                                                         defaultValue = "0") int page,
                                                                 @RequestParam(value = "pageSize",
                                                                         defaultValue = "10") int pageSize) {
        var stores = storeService.getAllStores(page, pageSize);
        return ResponseEntity.ok(stores);
    }

    @GetMapping("/store/{id}")
    public ResponseEntity<StoreResponseDto> getStoreById(@PathVariable UUID id) {
        var storeId = storeService.getStoreById(id);
        return ResponseEntity.ok(storeId);
    }

    @GetMapping("/brand/{brandId}/stores")
    public ResponseEntity<List<StoresBrandPaginationDto>> getByBrand(@RequestParam(value = "page",
                                                                                    defaultValue = "0") int page,
                                                                     @RequestParam(value = "pageSize",
                                                                                defaultValue = "10") int pageSize,
                                                                     @PathVariable String brandId){
        var listStores = storeService.getAllByBrand(page, pageSize, brandId);
        return ResponseEntity.ok(listStores);
    }



}

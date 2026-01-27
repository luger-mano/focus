package com.br.com.nava.focus.adapter.controller;


import com.br.com.nava.focus.adapter.dto.store.CreateStoreRequestDto;
import com.br.com.nava.focus.adapter.dto.store.StorePaginationDto;
import com.br.com.nava.focus.adapter.dto.store.StoreResponseDto;
import com.br.com.nava.focus.domain.model.Store;
import com.br.com.nava.focus.domain.repository.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class StoreController {


    private final StoreService storeService;


    @PostMapping("/store")
    public ResponseEntity<Store> createStore(@RequestBody CreateStoreRequestDto requestDto){
        var storeCreated = storeService.createStore(requestDto);
        return ResponseEntity.ok(storeCreated);
    }

    @GetMapping("/stores")
    public ResponseEntity<List<StorePaginationDto>> getAllStores(@RequestParam(value = "page",
                                                                           defaultValue = "0") int page,
                                                                 @RequestParam(value = "pageSize",
                                                                       defaultValue = "10") int pageSize){
        var stores = storeService.getAllStores(page, pageSize);
        return ResponseEntity.ok(stores);
    }

}

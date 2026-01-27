package com.br.com.nava.focus.adapter.controller;


import com.br.com.nava.focus.adapter.dto.CreateStoreRequestDto;
import com.br.com.nava.focus.adapter.dto.StoreResponseDto;
import com.br.com.nava.focus.domain.model.Store;
import com.br.com.nava.focus.domain.repository.StoreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<List<StoreResponseDto>> getAllStores(){
        storeService.getAllStores();
        return ResponseEntity.ok().build();
    }

}

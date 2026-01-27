package com.br.com.nava.focus.domain.service;

import com.br.com.nava.focus.adapter.dto.CreateStoreRequestDto;
import com.br.com.nava.focus.adapter.dto.StoreResponseDto;
import com.br.com.nava.focus.domain.model.Store;
import com.br.com.nava.focus.domain.repository.StoreMapper;
import com.br.com.nava.focus.domain.repository.StoreRepository;
import com.br.com.nava.focus.domain.repository.StoreService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreRepository storeRepository;

    // CRIAR ADDRESS SERVICE E REPOSITORY PARA SALVAR NO BANCO

    @Override
    @Transactional
    public Store createStore(CreateStoreRequestDto requestDto) {
        try {

            if (storeRepository.existsByContactEmail(requestDto.contact().getEmail())) {
                System.out.println("Caiu aqui");
                log.warn("Já existe uma loja cadastrada com o email: {}", requestDto.contact().getEmail());
                return new Store();
            }

            var storeEntity = requestDto.toEntity();
            System.out.println(storeEntity.getStoreId());
            var storeSaved = storeRepository.save(storeEntity);

            log.info("Loja salvada com sucesso {}", storeSaved);
            return new Store(storeSaved.getStoreId(), storeSaved.getName(), storeSaved.getContact(), storeSaved.getAddress(), storeSaved.getProducts());
        } catch (Exception e) {
            log.error("Não foi possível salvar uma loja no banco.");
            return new Store();
        }
    }

    @Override
    public List<StoreResponseDto> getAllStores() {
        var listAll = this.storeRepository.findAll();

        if (listAll.isEmpty()) {
            return new ArrayList<>();
        }

        return listAll.stream()
                .map(StoreMapper::toDto)
                .toList();
    }


}

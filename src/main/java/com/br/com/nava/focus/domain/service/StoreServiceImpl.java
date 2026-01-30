package com.br.com.nava.focus.domain.service;

import com.br.com.nava.focus.adapter.dto.store.CreateStoreRequestDto;
import com.br.com.nava.focus.adapter.dto.store.StorePaginationDto;
import com.br.com.nava.focus.adapter.dto.store.StoreResponseDto;
import com.br.com.nava.focus.domain.algorithms.Researcher;
import com.br.com.nava.focus.domain.algorithms.SearchStrategy;
import com.br.com.nava.focus.domain.model.Store;
import com.br.com.nava.focus.domain.repository.AddressService;
import com.br.com.nava.focus.domain.repository.StoreRepository;
import com.br.com.nava.focus.domain.repository.StoreService;
import com.br.com.nava.focus.mapper.StoreMapper;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;
    private final AddressService addressService;
    private final StoreMapper mapper;

    public StoreServiceImpl(StoreRepository storeRepository, AddressService addressService, StoreMapper mapper) {
        this.storeRepository = storeRepository;
        this.addressService = addressService;
        this.mapper = mapper;
    }


    @Override
    @Transactional
    public Store createStore(CreateStoreRequestDto requestDto) {
        try {

            if (storeRepository.existsByContactEmail(requestDto.contact().getEmail())) {
                log.warn("Já existe uma loja cadastrada com o email: {}", requestDto.contact().getEmail());
                return new Store();
            }

            var storeEntity = requestDto.toEntity();

            var addressSaved = addressService.saveAddress(requestDto.address(), storeEntity);

            storeEntity.setAddress(addressSaved);

            var storeSaved = storeRepository.save(storeEntity);

            log.info("Loja salvada com sucesso {}", storeSaved);
            return new Store(storeSaved.getStoreId(), storeSaved.getName(), storeSaved.getContact(), storeSaved.getAddress(), storeSaved.getProducts());
        } catch (Exception e) {
            log.error("Não foi possível salvar uma loja no banco.");
            return new Store();
        }
    }

    @Override
    public List<StorePaginationDto> getAllStores(int page, int pageSize) {
        var listAll = this.storeRepository.findAll(PageRequest.of(page,
                pageSize));


        return listAll.map(store -> new StorePaginationDto(
                        new StoreResponseDto(store.getName(),
                                store.getContact(),
                                store.getAddress(),
                                store.getProducts()),
                        page,
                        pageSize,
                        listAll.getTotalPages(),
                        listAll.getNumberOfElements()))
                .toList();
    }

    @Override
    public StoreResponseDto getStoreById(UUID id) {
        SearchStrategy<Store, UUID> strategy = new Researcher<>();

        List<Store> storesList = this.storeRepository.findAll();
        var storeArray = storesList.toArray(new Store[0]);

        Optional<Store> store = strategy.binarySearch(
                storeArray,
                id,
                Store::getStoreId,
                UUID::compareTo);

        if (store.isEmpty()) {
            throw new RuntimeException();
        }

        return mapper.toDto(store.get());
    }

}

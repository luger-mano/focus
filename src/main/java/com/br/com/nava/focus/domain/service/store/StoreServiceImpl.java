package com.br.com.nava.focus.domain.service.store;

import com.br.com.nava.focus.adapter.dto.store.CreateStoreRequestDto;
import com.br.com.nava.focus.adapter.dto.store.StorePaginationDto;
import com.br.com.nava.focus.adapter.dto.store.StoreResponseDto;
import com.br.com.nava.focus.domain.algorithms.researcher.Researcher;
import com.br.com.nava.focus.domain.algorithms.researcher.SearchStrategy;
import com.br.com.nava.focus.domain.model.Store;
import com.br.com.nava.focus.domain.repository.StoreRepository;
import com.br.com.nava.focus.domain.service.address.AddressService;
import com.br.com.nava.focus.domain.service.brand.BrandService;
import com.br.com.nava.focus.mapper.BrandMapper;
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
    private final BrandService brandService;
    private final StoreMapper storeMapper;
    private BrandMapper brandMapper;

    public StoreServiceImpl(StoreRepository storeRepository, AddressService addressService, BrandService brandService, StoreMapper storeMapper) {
        this.storeRepository = storeRepository;
        this.addressService = addressService;
        this.brandService = brandService;
        this.storeMapper = storeMapper;
    }

    @Override
    @Transactional
    public StoreResponseDto createStore(CreateStoreRequestDto requestDto, String brandId) {

        try {
            var storeEntity = requestDto.toEntity();

            if (storeRepository.existsByContactEmail(requestDto.contact().getEmail())) {
                log.warn("Já existe uma loja cadastrada com o email: {}", requestDto.contact().getEmail());
                return new StoreResponseDto(null, null);
            }

            var addressSaved = addressService.saveAddress(requestDto.address(), storeEntity);
            storeEntity.setAddress(addressSaved);

            var storeSaved = storeRepository.save(storeEntity);
            log.info("Loja criada com sucesso {}", storeSaved);

            return storeMapper.toDto(storeSaved);

        } catch (Exception e) {
            log.error("Não foi possível criar uma loja no banco.");
            return new StoreResponseDto(null, null);
        }
    }

    @Override
    public List<StorePaginationDto> getAllStores(int page, int pageSize) {
        var listAll = this.storeRepository.findAll(PageRequest.of(page,
                pageSize));


        return listAll.map(store -> new StorePaginationDto(
                        new StoreResponseDto(
                                store.getContact(),
                                store.getAddress()),
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

        return storeMapper.toDto(store.get());
    }

}

package com.br.com.nava.focus.domain.service.store;

import com.br.com.nava.focus.adapter.dto.address.AddressResponseDto;
import com.br.com.nava.focus.adapter.dto.store.*;
import com.br.com.nava.focus.domain.algorithms.researcher.Researcher;
import com.br.com.nava.focus.domain.algorithms.researcher.SearchStrategy;
import com.br.com.nava.focus.domain.model.Address;
import com.br.com.nava.focus.domain.model.Brand;
import com.br.com.nava.focus.domain.model.Store;
import com.br.com.nava.focus.domain.repository.StoreRepository;
import com.br.com.nava.focus.domain.service.address.AddressService;
import com.br.com.nava.focus.domain.service.brand.BrandService;
import com.br.com.nava.focus.mapper.StoreMapper;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;
    private final AddressService addressService;
    private final BrandService brandService;
    private final StoreMapper storeMapper;

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
            if (storeRepository.existsByContactEmail(requestDto.getContact().getEmail())) {
                log.warn("Já existe uma loja cadastrada com o email: {}", requestDto.getContact().getEmail());
                return new StoreResponseDto(null, null);
            }

            Store storeEntity = requestDto.toEntity();

            Brand brand = brandService.findById(brandId);
            storeEntity.setBrand(brand);

            Address addressSaved = addressService.saveAddress(requestDto.getAddress(), storeEntity);
            storeEntity.setAddress(addressSaved);

            Store storeSaved = storeRepository.save(storeEntity);
            log.info("Loja criada com sucesso {}", storeSaved);

            return storeMapper.toDto(storeSaved);
        } catch (Exception e) {
            log.error("Não foi possível criar uma loja no banco.");
            return new StoreResponseDto(null, null);
        }
    }

    @Override
    @Cacheable(cacheNames = "stores", key = "#page + '-' + #pageSize")
    public List<StorePaginationDto> getAllStores(int page, int pageSize) {
        var listAll = this.storeRepository.findAll(PageRequest.of(page,
                pageSize));

        return listAll.stream()
                .map(store -> new StorePaginationDto(
                        new StoreResponseDto(
                                store.getContact(),
                                new AddressResponseDto(
                                        store.getAddress().getCep(),
                                        store.getAddress().getLogradouro(),
                                        store.getAddress().getComplemento(),
                                        store.getAddress().getUnidade(),
                                        store.getAddress().getBairro(),
                                        store.getAddress().getLocalidade(),
                                        store.getAddress().getUf(),
                                        store.getAddress().getEstado())),
                        page,
                        pageSize,
                        listAll.getTotalPages(),
                        listAll.getNumberOfElements()))
                .collect(Collectors.toList());
    }

    @Override
    public List<StoresBrandPaginationDto> getAllByBrand(int page, int pageSize, String brandId) {
        var listAllBrands = storeRepository.findByBrand_BrandId(PageRequest.of(page, pageSize), brandId);

        return listAllBrands.stream()
                .map(store -> new StoresBrandPaginationDto(
                        new StoresBrandResponseDto(store.getBrand()),
                        page,
                        pageSize,
                        listAllBrands.getTotalPages(),
                        listAllBrands.getNumberOfElements()))
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

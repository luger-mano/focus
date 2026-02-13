package com.br.com.nava.focus.domain.repository;

import com.br.com.nava.focus.domain.model.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StoreRepository extends JpaRepository<Store, UUID> {

    boolean existsByEmail(String email);
    Page<Store> findByBrand_BrandId(Pageable pageable, String brandId);
}

package com.br.com.nava.focus.domain.repository;

import com.br.com.nava.focus.domain.model.StoreProduct;
import com.br.com.nava.focus.domain.model.StoreProductId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreProductRepository extends JpaRepository<StoreProduct, StoreProductId> {
}

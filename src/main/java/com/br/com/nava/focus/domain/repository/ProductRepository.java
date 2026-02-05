package com.br.com.nava.focus.domain.repository;

import com.br.com.nava.focus.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
}

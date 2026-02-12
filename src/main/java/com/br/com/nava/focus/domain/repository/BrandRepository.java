package com.br.com.nava.focus.domain.repository;

import com.br.com.nava.focus.domain.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand, String> {
    Optional<Brand> findByBrandId(String brandId);
}

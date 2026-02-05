package com.br.com.nava.focus.domain.service.product;

import com.br.com.nava.focus.domain.model.Product;

import java.util.UUID;

public interface ProductService {

    Product saveProduct(Product product);
    Product findById(UUID id);
}

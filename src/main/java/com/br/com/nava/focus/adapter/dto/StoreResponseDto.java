package com.br.com.nava.focus.adapter.dto;

import com.br.com.nava.focus.domain.model.Address;
import com.br.com.nava.focus.domain.model.Contact;
import com.br.com.nava.focus.domain.model.Product;
import com.br.com.nava.focus.domain.model.Store;
import com.br.com.nava.focus.domain.repository.StoreMapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record StoreResponseDto(@NotBlank(message = "O campo nome deve ser preenchido") String name,
                               @NotEmpty(message = "As informações de contato não podem estar vazias") Contact contact,
                               @NotEmpty(message = "As informações de endereço não podem estar vazias") Address address,
                               List<Product> products){

}

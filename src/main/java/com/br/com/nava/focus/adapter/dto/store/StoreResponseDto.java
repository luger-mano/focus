package com.br.com.nava.focus.adapter.dto.store;

import com.br.com.nava.focus.domain.model.Address;
import com.br.com.nava.focus.domain.model.Contact;
import com.br.com.nava.focus.domain.model.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.Set;

public record StoreResponseDto(@NotEmpty(message = "As informações de contato não podem estar vazias")
                               Contact contact,
                               @NotEmpty(message = "As informações de endereço não podem estar vazias")
                               Address address){

}

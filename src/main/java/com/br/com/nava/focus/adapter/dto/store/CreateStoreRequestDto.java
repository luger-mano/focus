package com.br.com.nava.focus.adapter.dto.store;

import com.br.com.nava.focus.adapter.dto.address.AddressRequestDto;
import com.br.com.nava.focus.domain.model.Address;
import com.br.com.nava.focus.domain.model.Contact;
import com.br.com.nava.focus.domain.model.Product;
import com.br.com.nava.focus.domain.model.Store;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;
import java.util.UUID;

public record CreateStoreRequestDto(UUID storeId,
                                    @NotBlank(message = "O campo nome deve ser preenchido") String name,
                                    Contact contact,
                                    @NotEmpty(message = "As informações de endereço não podem estar vazias") AddressRequestDto address,
                                    List<Product> products) {

    public Store toEntity() {
        Store store = new Store();
        store.setStoreId(storeId);
        store.setName(this.name);
        store.setContact(this.contact());
        store.setAddress(address.toEntity());
        store.setProducts(this.products());
        return store;
    }

}

package com.br.com.nava.focus.adapter.dto.store;

import com.br.com.nava.focus.adapter.dto.address.AddressRequestDto;
import com.br.com.nava.focus.domain.model.Contact;
import com.br.com.nava.focus.domain.model.Product;
import com.br.com.nava.focus.domain.model.Store;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.HashSet;

public record CreateStoreRequestDto(@NotBlank(message = "O campo nome deve ser preenchido")
                                    String name,
                                    @NotEmpty
                                    Contact contact,
                                    @NotEmpty(message = "As informações de endereço não podem estar vazias")
                                    AddressRequestDto address) {

    public Store toEntity() {
        Store store = new Store();
        store.setName(name);
        store.setContact(this.contact);
        store.setAddress(address.toEntity());
        return store;
    }

}

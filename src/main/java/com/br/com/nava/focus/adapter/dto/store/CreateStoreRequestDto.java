package com.br.com.nava.focus.adapter.dto.store;

import com.br.com.nava.focus.adapter.dto.address.AddressRequestDto;
import com.br.com.nava.focus.domain.model.Contact;
import com.br.com.nava.focus.domain.model.Store;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateStoreRequestDto {

    @NotBlank(message = "O campo nome deve ser preenchido")
    private String name;
    @NotEmpty
    private Contact contact;
    @NotEmpty(message = "As informações de endereço não podem estar vazias")
    private AddressRequestDto address;

    public Store toEntity() {
        Store store = new Store();
        store.setName(name);
        store.setContact(this.contact);
        store.setAddress(address.toEntity());
        return store;
    }

}

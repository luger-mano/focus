package com.br.com.nava.focus.adapter.dto.store;

import com.br.com.nava.focus.adapter.dto.address.AddressRequestDto;
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
    @NotBlank
    private String phoneNumber;
    @NotBlank
    private String email;
    @NotBlank
    private String cnpj;
    @NotEmpty(message = "As informações de endereço não podem estar vazias")
    private AddressRequestDto address;

    public Store toEntity() {
        Store store = new Store();
        store.setName(this.getName());
        store.setPhoneNumber(this.getPhoneNumber());
        store.setEmail(this.getEmail());
        store.setCnpj(this.getCnpj());
        store.setAddress(getAddress().toEntity());
        return store;
    }

}

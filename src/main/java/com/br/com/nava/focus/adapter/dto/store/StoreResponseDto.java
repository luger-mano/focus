package com.br.com.nava.focus.adapter.dto.store;

import com.br.com.nava.focus.adapter.dto.address.AddressResponseDto;
import com.br.com.nava.focus.domain.model.Contact;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoreResponseDto {

    private Contact contac;
    private AddressResponseDto address;

    public StoreResponseDto(Contact contac, AddressResponseDto address) {
        this.contac = contac;
        this.address = address;
    }

}

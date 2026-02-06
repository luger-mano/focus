package com.br.com.nava.focus.adapter.dto.store;

import com.br.com.nava.focus.adapter.dto.address.AddressResponseDto;
import com.br.com.nava.focus.domain.model.Contact;

public record StoreResponseDto(Contact contact,
                               AddressResponseDto address){

}

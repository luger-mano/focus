package com.br.com.nava.focus.domain.service.address;

import com.br.com.nava.focus.adapter.dto.address.AddressRequestDto;
import com.br.com.nava.focus.domain.model.Address;
import com.br.com.nava.focus.domain.model.Store;
import com.br.com.nava.focus.domain.repository.ViaCepIntegration;
import jakarta.validation.Valid;

public interface AddressService extends ViaCepIntegration {

    Address saveAddress(@Valid AddressRequestDto addressRequestDto, Store store);
}

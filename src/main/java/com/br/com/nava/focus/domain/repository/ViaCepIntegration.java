package com.br.com.nava.focus.domain.repository;

import com.br.com.nava.focus.adapter.dto.address.AddressResponseDto;

public interface ViaCepIntegration {
    AddressResponseDto getAddressByCep(String cep);
}

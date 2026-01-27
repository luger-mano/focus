package com.br.com.nava.focus.domain.repository;

import com.br.com.nava.focus.domain.model.Address;

public interface ViaCepIntegration {
    Address getAddressByCep(String cep);
}

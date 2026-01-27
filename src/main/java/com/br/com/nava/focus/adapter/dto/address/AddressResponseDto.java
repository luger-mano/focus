package com.br.com.nava.focus.adapter.dto.address;

public record AddressResponseDto(String cep,
                                 String logradouro,
                                 String complemento,
                                 String unidade,
                                 String bairro,
                                 String localidade,
                                 String uf,
                                 String estado) {
}

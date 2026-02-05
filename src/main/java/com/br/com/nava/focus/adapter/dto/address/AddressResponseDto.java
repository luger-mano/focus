package com.br.com.nava.focus.adapter.dto.address;

import com.br.com.nava.focus.domain.model.Address;

public record AddressResponseDto(String cep,
                                 String logradouro,
                                 String complemento,
                                 String unidade,
                                 String bairro,
                                 String localidade,
                                 String uf,
                                 String estado) {
    public Address toEntity(){
        Address address = new Address();

        address.setCep(cep);
        address.setLogradouro(logradouro);
        address.setComplemento(complemento);
        address.setUnidade(unidade);
        address.setBairro(bairro);
        address.setLocalidade(localidade);
        address.setUf(uf);
        address.setEstado(estado);

        return address;
    }
}

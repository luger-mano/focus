package com.br.com.nava.focus.adapter.dto.address;

import com.br.com.nava.focus.domain.model.Address;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressRequestDto {
    @Min(value = 8, message = "O cep deve ter no mínimo 8 caracteres.")
    @Max(value = 8, message = "O cep deve ter no máximo 8 caracteres.")
    private String cep;
    @Min(value = 3, message = "Logradouro deve ter no minimo 3 caracteres.")
    private String logradouro;
    private String complemento;
    private String unidade;
    private String bairro;
    @Min(value = 3, message = "Localidade deve ter no minimo 3 caracteres.")
    private String localidade;
    private String uf;
    private String estado;

    public Address toEntity() {
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

    public AddressRequestDto(String cep, String logradouro, String complemento, String unidade, String bairro, String localidade, String uf, String estado) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.complemento = complemento;
        this.unidade = unidade;
        this.bairro = bairro;
        this.localidade = localidade;
        this.uf = uf;
        this.estado = estado;
    }

    public AddressRequestDto() {
    }
}

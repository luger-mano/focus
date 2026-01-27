package com.br.com.nava.focus.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Setter
@Getter
@Entity
@Table(name = "TABLE_ADDRESSES")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long addressId;
    private String logradouro;
    private String complemento;
    private String unidade;
    private String bairro;
    private String localidade;
    private String uf;
    private String estado;
    private String regiao;
    private String ibge;
    private String gia;
    private String siafi;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "store_id", unique = true)
    private Store store;

    public Address(Long addressId, String logradouro, String complemento, String unidade, String bairro, String localidade, String uf, String estado, String regiao, String ibge, String gia, String siafi) {
        this.addressId = addressId;
        this.logradouro = logradouro;
        this.complemento = complemento;
        this.unidade = unidade;
        this.bairro = bairro;
        this.localidade = localidade;
        this.uf = uf;
        this.estado = estado;
        this.regiao = regiao;
        this.ibge = ibge;
        this.gia = gia;
        this.siafi = siafi;
    }

    public Address() {

    }


}
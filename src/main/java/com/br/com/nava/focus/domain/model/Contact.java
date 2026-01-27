package com.br.com.nava.focus.domain.model;


import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Contact {

    private String phoneNumber;
    private String email;
    private String cnpj;

    public Contact(String phoneNumber, String email, String cnpj) {
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.cnpj = cnpj;
    }

    public Contact() {
    }
}

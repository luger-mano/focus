package com.br.com.nava.focus.adapter.dto.user;


import com.br.com.nava.focus.adapter.dto.address.AddressResponseDto;
import com.br.com.nava.focus.adapter.dto.store.StoreResponseDto;
import com.br.com.nava.focus.domain.model.Role;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Set;

@Getter
@Setter
public class UserResponseDto {

    private String fullName;
    private String email;
    private String password;
    private String cpf;
    private String phone;
    private Instant creationAt;
    private Instant updateAt;
    private AddressResponseDto address;
    private Set<Role> roles;
    private StoreResponseDto store;

    public UserResponseDto(String fullName, String email, String password, String cpf, String phone, Instant creationAt, Instant updateAt, AddressResponseDto address, Set<Role> roles, StoreResponseDto store) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.cpf = cpf;
        this.phone = phone;
        this.creationAt = creationAt;
        this.updateAt = updateAt;
        this.address = address;
        this.roles = roles;
        this.store = store;
    }
}

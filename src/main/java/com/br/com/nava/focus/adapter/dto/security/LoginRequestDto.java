package com.br.com.nava.focus.adapter.dto.security;

import com.br.com.nava.focus.domain.model.Address;
import com.br.com.nava.focus.domain.model.Role;

import java.util.Date;
import java.util.Set;

public record LoginRequestDto(
        String fullName,
        String email,
        String password,
        String cpf,
        String phone,
        Date enteredAt,
        Date creationAt,
        Date updateAt,
        Date deletedAt,
        Address address,
        Set<Role> roles) {

}

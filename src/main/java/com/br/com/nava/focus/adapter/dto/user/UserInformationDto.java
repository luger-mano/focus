package com.br.com.nava.focus.adapter.dto.user;

import com.br.com.nava.focus.adapter.dto.address.AddressResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class UserInformationDto {

    private String fullName;
    private String email;
    private String password;
    private String cpf;
    private String phone;
    private Instant creationAt;
    private Instant updateAt;
    private AddressResponseDto address;

    public UserInformationDto(String fullName, String email, String password, String cpf, String phone, Instant creationAt, Instant updateAt, AddressResponseDto address) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.cpf = cpf;
        this.phone = phone;
        this.creationAt = creationAt;
        this.updateAt = updateAt;
        this.address = address;
    }

    public UserInformationDto() {
    }
}

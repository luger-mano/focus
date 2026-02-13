package com.br.com.nava.focus.adapter.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserResponseDto {

    private String fullName;
    private String email;

    public CreateUserResponseDto(String fullName, String email) {
        this.fullName = fullName;
        this.email = email;
    }

    public CreateUserResponseDto() {
    }
}

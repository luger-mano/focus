package com.br.com.nava.focus.adapter.dto.security;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDto {

    private String accessToken;

    private Long expiresIn;

    public LoginResponseDto(String accessToken, Long expiresIn) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
    }

    public LoginResponseDto() {
    }
}



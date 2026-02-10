package com.br.com.nava.focus.adapter.dto.security;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class LoginRequestDto {

    @NotBlank(message = "O campo email deve ser preenchido.")
    String email;

    @NotBlank(message = "O campo password deve ser preenchido.")
    String password;

    public LoginRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}

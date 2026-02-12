package com.br.com.nava.focus.adapter.dto.security;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDto {

    @NotBlank(message = "O campo email deve ser preenchido.")
    private String email;

    @NotBlank(message = "O campo password deve ser preenchido.")
    private String password;

    public LoginRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}

package com.br.com.nava.focus.adapter.dto.user;

import com.br.com.nava.focus.domain.model.Address;
import com.br.com.nava.focus.domain.model.Role;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

import java.util.Date;
import java.util.Set;

public record CreateUserRequestDto(
        @NotEmpty(message = "O campo fullName deve ser preenchido.")
        @Min(value = 10)
        @Max(value = 65)
        String fullName,
        @Email
        String email,
        @NotBlank(message = "O campo password deve ser preenchido.")
        @Min(value = 4)
        @Max(value = 12)
        String password,
        @CPF
        String cpf,
        @Pattern(regexp = "^\\([1-9]{2}\\) (?:[2-8]|9[0-9])[0-9]{3}-[0-9]{4}$")
        String phone,
        @PastOrPresent
        Date enteredAt,
        @PastOrPresent
        Date creationAt,
        @PastOrPresent
        Date updateAt,
        @PastOrPresent
        Date deletedAt,
        @NotEmpty(message = "O campo address deve ser preenchido.")
        Address address,
        Set<Role> roles) {
}

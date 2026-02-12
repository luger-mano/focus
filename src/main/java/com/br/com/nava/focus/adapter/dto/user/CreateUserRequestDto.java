package com.br.com.nava.focus.adapter.dto.user;

import com.br.com.nava.focus.domain.model.Address;
import com.br.com.nava.focus.domain.model.Role;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
public class CreateUserRequestDto{
        @NotEmpty(message = "O campo fullName deve ser preenchido.")
        @Min(value = 10)
        @Max(value = 65)
        private String fullName;
        @Email
        private String email;
        @NotBlank(message = "O campo password deve ser preenchido.")
        @Min(value = 4)
        @Max(value = 12)
        private String password;
        @CPF
        private String cpf;
        @Pattern(regexp = "^\\([1-9]{2}\\) (?:[2-8]|9[0-9])[0-9]{3}-[0-9]{4}$")
        private String phone;
        @PastOrPresent
        private Date enteredAt;
        @PastOrPresent
        private Date creationAt;
        @PastOrPresent
        private Date updateAt;
        @PastOrPresent
        private Date deletedAt;
        @NotEmpty(message = "O campo address deve ser preenchido.")
        private Address address;
        private Set<Role> roles;
}

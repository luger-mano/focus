package com.br.com.nava.focus.adapter.dto.admin;

import com.br.com.nava.focus.adapter.dto.user.CreateUserRequestDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAdminRequestDto {

    private CreateUserRequestDto userDto;
    private Long salary;

    public CreateAdminRequestDto(CreateUserRequestDto userDto) {
        this.userDto = userDto;
    }

    public CreateAdminRequestDto() {
    }
}

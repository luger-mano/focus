package com.br.com.nava.focus.adapter.dto.employee;

import com.br.com.nava.focus.adapter.dto.user.CreateUserResponseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateEmployeeResponseDto {

    private CreateUserResponseDto responseDto;

    public CreateEmployeeResponseDto(CreateUserResponseDto responseDto) {
        this.responseDto = responseDto;
    }
}

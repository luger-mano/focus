package com.br.com.nava.focus.adapter.dto.employee;

import com.br.com.nava.focus.adapter.dto.user.CreateUserRequestDto;
import com.br.com.nava.focus.domain.model.Employee;
import com.br.com.nava.focus.domain.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateEmployeeRequestDto {

    private CreateUserRequestDto userDto;
    private Long salary;

    public CreateEmployeeRequestDto(CreateUserRequestDto userDto, Long salary) {
        this.userDto = userDto;
        this.salary = salary;
    }


}

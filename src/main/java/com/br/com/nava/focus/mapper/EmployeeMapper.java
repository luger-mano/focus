package com.br.com.nava.focus.mapper;

import com.br.com.nava.focus.adapter.dto.employee.CreateEmployeeResponseDto;
import com.br.com.nava.focus.domain.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    @Mapping(source = "user.fullName", target = "responseDto.fullName")
    @Mapping(source = "user.email", target = "responseDto.email")
    CreateEmployeeResponseDto employeeEntityToCreateEmployeeResponseDto(Employee employee);

}

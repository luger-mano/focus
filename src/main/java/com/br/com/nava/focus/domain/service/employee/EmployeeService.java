package com.br.com.nava.focus.domain.service.employee;

import com.br.com.nava.focus.adapter.dto.employee.CreateEmployeeRequestDto;
import com.br.com.nava.focus.adapter.dto.employee.CreateEmployeeResponseDto;

import java.util.UUID;

public interface EmployeeService {

    CreateEmployeeResponseDto createEmployee(CreateEmployeeRequestDto dto, UUID storeId);
}

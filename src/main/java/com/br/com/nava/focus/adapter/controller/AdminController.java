package com.br.com.nava.focus.adapter.controller;


import com.br.com.nava.focus.adapter.dto.employee.CreateEmployeeRequestDto;
import com.br.com.nava.focus.adapter.dto.employee.CreateEmployeeResponseDto;
import com.br.com.nava.focus.adapter.dto.user.CreateUserRequestDto;
import com.br.com.nava.focus.adapter.dto.user.CreateUserResponseDto;
import com.br.com.nava.focus.adapter.dto.user.UserResponseDto;
import com.br.com.nava.focus.domain.service.employee.EmployeeService;
import com.br.com.nava.focus.domain.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AdminController {

    private final UserService userService;
    private final EmployeeService employeeService;

    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @PostMapping("/admin/store/{storeId}/user")
    public ResponseEntity<CreateUserResponseDto> createUser(@RequestBody CreateUserRequestDto dto, @PathVariable UUID storeId){
        var user = userService.createUser(dto, storeId);
        return ResponseEntity.ok(user);
    }

    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @PostMapping("/admin/store/{storeId}/employee")
    public ResponseEntity<CreateEmployeeResponseDto> createEmployee(@RequestBody CreateEmployeeRequestDto dto, @PathVariable UUID storeId){
        CreateEmployeeResponseDto employee = employeeService.createEmployee(dto, storeId);
        return ResponseEntity.ok(employee);
    }

    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @GetMapping("/admin/users")
    public ResponseEntity<List<UserResponseDto>> getUsers(){
        List<UserResponseDto> users = userService.getUsers();

        return ResponseEntity.ok(users);
    }

}

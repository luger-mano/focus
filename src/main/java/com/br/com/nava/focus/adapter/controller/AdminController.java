package com.br.com.nava.focus.adapter.controller;


import com.br.com.nava.focus.adapter.dto.admin.CreateAdminRequestDto;
import com.br.com.nava.focus.adapter.dto.employee.CreateEmployeeRequestDto;
import com.br.com.nava.focus.adapter.dto.employee.CreateEmployeeResponseDto;
import com.br.com.nava.focus.adapter.dto.user.UserResponseDto;
import com.br.com.nava.focus.domain.service.admin.AdminService;
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
    private final AdminService adminService;

    @PreAuthorize("hasAuthority('SCOPE_ADMIN') or hasAuthority('SCOPE_SUPER_ADMIN')")
    @PostMapping("/admin/store/{storeId}/employee")
    public ResponseEntity<CreateEmployeeResponseDto> createEmployee(@RequestBody CreateEmployeeRequestDto dto, @PathVariable UUID storeId) {
        CreateEmployeeResponseDto employee = employeeService.createEmployee(dto, storeId);
        return ResponseEntity.ok(employee);
    }

    @PreAuthorize("hasAuthority('SCOPE_SUPER_ADMIN')")
    @PostMapping("/super/store/{storeId}/admin")
    public ResponseEntity<?> createAdmin(@RequestBody CreateAdminRequestDto dto, @PathVariable UUID storeId){
        adminService.createAdmin(dto, storeId);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @GetMapping("/admin/users")
    public ResponseEntity<List<UserResponseDto>> getUsers() {
        List<UserResponseDto> users = userService.getUsers();

        return ResponseEntity.ok(users);
    }
    @PreAuthorize("hasAuthority('SCOPE_SUPER_ADMIN') or hasAuthority('SCOPE_ADMIN')")
    @PutMapping("/store/{storeId}/employee/{employeeId}/update")
    public ResponseEntity<Void> updateUser(@RequestBody CreateEmployeeRequestDto dto, @PathVariable UUID employeeId, @PathVariable UUID storeId){
        adminService.updateEmployee(dto, employeeId, storeId);
        return ResponseEntity.ok().build();
    }


}

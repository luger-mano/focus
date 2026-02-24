package com.br.com.nava.focus.domain.service.admin;

import com.br.com.nava.focus.adapter.dto.admin.CreateAdminRequestDto;

import java.util.UUID;

public interface AdminService {
    void createAdmin(CreateAdminRequestDto dto, UUID storeId);
}

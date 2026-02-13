package com.br.com.nava.focus.domain.service.user;

import com.br.com.nava.focus.adapter.dto.user.CreateUserRequestDto;
import com.br.com.nava.focus.adapter.dto.user.CreateUserResponseDto;
import com.br.com.nava.focus.adapter.dto.user.UserResponseDto;
import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

public interface UserService {

    CreateUserResponseDto createUser(@Valid CreateUserRequestDto dto, UUID storeId);

    List<UserResponseDto> getUsers();

}

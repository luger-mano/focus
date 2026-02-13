package com.br.com.nava.focus.mapper;


import com.br.com.nava.focus.adapter.dto.user.CreateUserRequestDto;
import com.br.com.nava.focus.adapter.dto.user.CreateUserResponseDto;
import com.br.com.nava.focus.adapter.dto.user.UserResponseDto;
import com.br.com.nava.focus.domain.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    CreateUserResponseDto userEntityToCreateUserResponseDto(User user);

    UserResponseDto userEntityToUserResponseDto(User user);

    User createUserRequestDtoToUserEntity(CreateUserRequestDto dto);
}

package com.br.com.nava.focus.mapper;


import com.br.com.nava.focus.adapter.dto.security.LoginRequestDto;
import com.br.com.nava.focus.domain.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User LoginRequestDtoToEntity(LoginRequestDto dto);

}

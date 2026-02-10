package com.br.com.nava.focus.domain.service.security;

import com.br.com.nava.focus.adapter.dto.security.LoginRequestDto;
import com.br.com.nava.focus.adapter.dto.security.LoginResponseDto;

public interface LoginService {

    LoginResponseDto login(LoginRequestDto dto);
}

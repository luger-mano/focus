package com.br.com.nava.focus.adapter.controller;


import com.br.com.nava.focus.adapter.dto.security.LoginRequestDto;
import com.br.com.nava.focus.adapter.dto.security.LoginResponseDto;
import com.br.com.nava.focus.adapter.dto.user.CreateUserRequestDto;
import com.br.com.nava.focus.adapter.dto.user.CreateUserResponseDto;
import com.br.com.nava.focus.domain.service.security.LoginService;
import com.br.com.nava.focus.domain.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;
    private final LoginService loginService;

    @PostMapping("/store/{storeId}/user")
    public ResponseEntity<CreateUserResponseDto> signup(@RequestBody CreateUserRequestDto dto, @PathVariable UUID storeId){
        var user = userService.createUser(dto, storeId);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody @Valid LoginRequestDto dto){
        LoginResponseDto login = loginService.login(dto);

        return ResponseEntity.ok(login);
    }
}

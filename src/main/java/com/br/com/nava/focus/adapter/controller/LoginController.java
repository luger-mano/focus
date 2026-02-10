package com.br.com.nava.focus.adapter.controller;


import com.br.com.nava.focus.adapter.dto.security.LoginRequestDto;
import com.br.com.nava.focus.adapter.dto.security.LoginResponseDto;
import com.br.com.nava.focus.domain.service.security.LoginService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody @Valid LoginRequestDto dto){
        LoginResponseDto login = loginService.login(dto);

        return ResponseEntity.ok(login);
    }
}

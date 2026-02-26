package com.br.com.nava.focus.adapter.controller;

import com.br.com.nava.focus.adapter.dto.user.CreateUserRequestDto;
import com.br.com.nava.focus.adapter.dto.user.UserResponseDto;
import com.br.com.nava.focus.domain.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;

    @PutMapping("/store/{storeId}/user/{userId}/update")
    public ResponseEntity<UserResponseDto> updateUserDate(@RequestBody CreateUserRequestDto dto, @PathVariable UUID userId, @PathVariable UUID storeId){
        var userUpdated = userService.updateUser(dto, userId, storeId);
        return ResponseEntity.ok(userUpdated);
    }

}

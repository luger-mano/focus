package com.br.com.nava.focus.adapter.controller;

import com.br.com.nava.focus.adapter.dto.user.CreateUserRequestDto;
import com.br.com.nava.focus.adapter.dto.user.CreateUserResponseDto;
import com.br.com.nava.focus.adapter.dto.user.UserInformationDto;
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

    @GetMapping("/user/dataUser")
    public ResponseEntity<UserInformationDto> getUserInformation(@RequestParam("userId") UUID userId){
        var userInformation = userService.getUserInformation(userId);
        return ResponseEntity.ok(userInformation);
    }
    @PostMapping("/store/{storeId}/user")
    public ResponseEntity<CreateUserResponseDto> signup(@RequestBody CreateUserRequestDto dto, @PathVariable UUID storeId){
        var user = userService.createUser(dto, storeId);
        return ResponseEntity.ok(user);
    }
    @PreAuthorize("hasAuthority('SCOPE_SUPER_ADMIN') or hasAuthority('SCOPE_ADMIN') or hasAuthority('SCOPE_CLIENT')")
    @PutMapping("/store/{storeId}/user/{userId}/update")
    public ResponseEntity<UserResponseDto> updateUser(@RequestBody CreateUserRequestDto dto, @PathVariable UUID userId, @PathVariable UUID storeId){
        var userUpdated = userService.updateUser(dto, userId, storeId);
        return ResponseEntity.ok(userUpdated);
    }

    @PreAuthorize("hasAuthority('SCOPE_SUPER_ADMIN') or hasAuthority('SCOPE_ADMIN') or hasAuthority('SCOPE_CLIENT')")
    @DeleteMapping("/user/delete")
    public ResponseEntity<Void> deleteUser(@RequestParam("userId") UUID userId){
        userService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }

}

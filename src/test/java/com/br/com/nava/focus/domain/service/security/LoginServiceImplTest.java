package com.br.com.nava.focus.domain.service.security;

import com.br.com.nava.focus.adapter.dto.security.LoginRequestDto;
import com.br.com.nava.focus.adapter.dto.security.LoginResponseDto;
import com.br.com.nava.focus.domain.model.User;
import com.br.com.nava.focus.domain.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoginServiceImplTest {

    @Mock
    private JwtEncoder jwtEncoder;
    @Mock
    private UserRepository userRepository;
    @Mock
    private BCryptPasswordEncoder passwordEncoder;
    @InjectMocks
    private LoginServiceImpl loginService;

    @Test
    @DisplayName("[1 - Scenario]: ['The method should return a user with correct email and password']")
    void Scenario1_login_with_correct_email_and_password() {
        //Given
        LoginRequestDto request = new LoginRequestDto("admin@admin.gmail", "3571");

        User userMock = mock(User.class);
        when(userMock.getUserId()).thenReturn(UUID.randomUUID());
        when(userMock.getRoles()).thenReturn(Set.of());

        when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.of(userMock));

        when(userMock.isLoginCorrect(request, passwordEncoder)).thenReturn(true);

        Jwt jwt = mock(Jwt.class);
        when(jwt.getTokenValue()).thenReturn("token-gerado-fake");
        when(jwtEncoder.encode(any(JwtEncoderParameters.class))).thenReturn(jwt);

        //When
        LoginResponseDto response = loginService.login(request);

        //Then
        assertNotNull(response);
        assertEquals("token-gerado-fake", response.getAccessToken());
        assertEquals(300L, response.getExpiresIn());
        verify(userRepository).findByEmail(request.getEmail());

    }

    @Test
    @DisplayName("[2 - Scenario]: ['The method shouldn't return a user with correct email and password']")
    void Scenario2_login_with_incorrect_email_and_password() {
        //Given
        LoginRequestDto request = new LoginRequestDto("admin@admin.gmail", "3571");
        when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.empty());

        //When e Then
        assertThrows(BadCredentialsException.class, () -> loginService.login(request));
        verify(userRepository).findByEmail(request.getEmail());

    }



}
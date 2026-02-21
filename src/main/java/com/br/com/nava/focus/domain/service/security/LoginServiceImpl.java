package com.br.com.nava.focus.domain.service.security;

import com.br.com.nava.focus.adapter.dto.security.LoginRequestDto;
import com.br.com.nava.focus.adapter.dto.security.LoginResponseDto;
import com.br.com.nava.focus.domain.model.Role;
import com.br.com.nava.focus.domain.model.User;
import com.br.com.nava.focus.domain.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LoginServiceImpl implements LoginService {

    private final JwtEncoder jwtEncoder;
    private final UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public LoginServiceImpl(JwtEncoder jwtEncoder, UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.jwtEncoder = jwtEncoder;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public LoginResponseDto login(LoginRequestDto dto) {

        Optional<User> user = userRepository.findByEmail(dto.getEmail());

        if (user.isEmpty() || !user.get().isLoginCorrect(dto, passwordEncoder)){
            throw new BadCredentialsException("Email ou Senha inválidos.");
        }

            Instant now = Instant.now();
            long expiresIn = 300L;

            String scopes = user.get()
                    .getRoles()
                    .stream()
                    .map(Role::getName)
                    .collect(Collectors.joining(" "));

            JwtClaimsSet claims = JwtClaimsSet.builder()
                    .issuer("mybackend")
                    .subject(user.get().getUserId().toString())
                    .issuedAt(now)
                    .expiresAt(now.plusSeconds(expiresIn))
                    .claim("scope", scopes)
                    .build();

            String jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

            LoginResponseDto response = new LoginResponseDto();
            response.setAccessToken(jwtValue);
            response.setExpiresIn(expiresIn);

            return response;
    }
}

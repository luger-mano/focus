package com.br.com.nava.focus.domain.repository;

import com.br.com.nava.focus.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByFullName(String admin);
    Optional<User> findByEmail(String admin);
    Boolean existsByEmail(String email);
}

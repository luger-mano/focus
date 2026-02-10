package com.br.com.nava.focus.domain.repository;

import com.br.com.nava.focus.domain.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}

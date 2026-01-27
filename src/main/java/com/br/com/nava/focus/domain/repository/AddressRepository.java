package com.br.com.nava.focus.domain.repository;

import com.br.com.nava.focus.domain.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}

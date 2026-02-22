package com.br.com.nava.focus.domain.repository;

import com.br.com.nava.focus.domain.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
}

package com.br.com.nava.focus.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "TABLE_EMPLOYEES")
@Getter
@Setter
public class Employee{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID employeeId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name = "store_id")
    @JsonIgnore
    private Store store;

    @Column(name = "salary", nullable = false)
    private Long salary;

    @Column(name = "hire_date")
    @PastOrPresent
    private LocalDate hireDate;


    public Employee(UUID employeeId, User user, Store store, Long salary, LocalDate hireDate) {
        this.employeeId = employeeId;
        this.user = user;
        this.store = store;
        this.salary = salary;
        this.hireDate = hireDate;
    }

    public Employee() {

    }
}

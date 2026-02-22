package com.br.com.nava.focus.domain.model;


import com.br.com.nava.focus.adapter.dto.security.LoginRequestDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "TABLE_USERS")
@Getter
@Setter
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userId;
    private String fullName;
    private String email;
    private String password;
    private String cpf;
    private String phone;
    @CreationTimestamp
    private Instant creationAt;
    @UpdateTimestamp
    private Instant updateAt;
    @OneToOne
    @JoinColumn(name = "address_id")
    @JsonIgnore
    private Address address;
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "TABLE_USERS_ROLES",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    @ManyToOne
    @JoinColumn(name = "store_id")
    @JsonIgnore
    private Store store;

    @OneToOne
    @JoinColumn(name = "employee_id")
    @JsonIgnore
    private Employee employee;

    public User() {
    }

    public User(UUID userId, String fullName, String email, String password, String cpf, String phone, Instant creationAt, Instant updateAt, Address address, Set<Role> roles, Store store) {
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.cpf = cpf;
        this.phone = phone;
        this.creationAt = creationAt;
        this.updateAt = updateAt;
        this.address = address;
        this.roles = roles;
        this.store = store;
    }

    public boolean isLoginCorrect(LoginRequestDto dto, PasswordEncoder passwordEncoder){
        return passwordEncoder.matches(dto.getPassword(), this.password);
    }
}

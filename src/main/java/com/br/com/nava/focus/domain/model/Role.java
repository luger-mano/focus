package com.br.com.nava.focus.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TABLE_ROLES")
@Getter
@Setter
public class Role {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;
    private String name;

    @Getter
    public enum Values{
        SUPER_ADMIN(1L),
        ADMIN(2L),
        EMPLOYEE(3L),
        CLIENT(4L);

        final long roleId;

        Values(long roleId) {
            this.roleId = roleId;
        }
    }

    public Role(Long roleId, String name) {
        this.roleId = roleId;
        this.name = name;
    }

    public Role() {
    }
}

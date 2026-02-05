package com.br.com.nava.focus.domain.model;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "TABLE_ROLES")
@Getter
public class Role {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;
    private String name;

    @Getter
    public enum Values{
        ADMIN(1L),
        EMPLOYEE(2L);

        final long roleId;

        Values(long roleId) {
            this.roleId = roleId;
        }
    }

}

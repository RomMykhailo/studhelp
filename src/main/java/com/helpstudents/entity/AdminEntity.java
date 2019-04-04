package com.helpstudents.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "admins")
public class AdminEntity extends BaseEntity {

    @Column(name = "email", nullable = false, unique = true, length = 45)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @NotBlank
    private String phoneNumber;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "role_admin",
            joinColumns = @JoinColumn(name = "admin_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<RoleEntity> roles;
}

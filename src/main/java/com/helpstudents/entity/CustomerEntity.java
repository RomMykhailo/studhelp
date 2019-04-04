package com.helpstudents.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customers")
public class CustomerEntity extends BaseEntity {

    @Column(name = "email", nullable = false, unique = true, length = 45)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    private String phoneNumber;
    @Column(nullable = false, unique = true, length = 45)
    private String nickName;

    private LocalDateTime dateCreate;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleEntity role;
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "role_customer",
//            joinColumns = @JoinColumn(name = "customer_id"),
//            inverseJoinColumns = @JoinColumn(name = "role_id")
//    )
//    private List<RoleEntity> roles;
}

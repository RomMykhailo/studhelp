package com.helpstudents.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "workers")
public class WorkerEntity extends BaseEntity {

    @Column(name = "email", nullable = false, unique = true, length = 45)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    private String phoneNumber;
    @Column(nullable = false)
    private String nickName;

    @Column(name = "description")
    private String description;

    @Column(columnDefinition = "DECIMAL(5,2)")
    private BigDecimal money;

    private LocalDateTime dateCreate;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "role_worker",
           joinColumns = @JoinColumn(name = "worker_id"),
           inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<RoleEntity> roles;
}

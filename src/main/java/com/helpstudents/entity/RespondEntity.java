package com.helpstudents.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table
public class RespondEntity extends BaseEntity {

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "worker_id")
    private WorkerEntity workerEntity;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customerEntity;

    private boolean isDeleted;

}

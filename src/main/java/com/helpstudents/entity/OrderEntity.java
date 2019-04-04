package com.helpstudents.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class OrderEntity extends BaseEntity {

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;
    @Column(nullable = false)
    private LocalDateTime dateCreate;
    private LocalDateTime dateMade;

    private String file;

    private String status;

    @Column(columnDefinition = "DECIMAL(5,2)")
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "worker_id")
    private WorkerEntity workerEntity;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customerEntity;

}

package com.helpstudents.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "prices")
public class PricePropositionEntity extends  BaseEntity {

    @Column(columnDefinition = "DECIMAL(5,2)")
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "worker_id")
    private WorkerEntity workerEntity;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity orderEntity;
}

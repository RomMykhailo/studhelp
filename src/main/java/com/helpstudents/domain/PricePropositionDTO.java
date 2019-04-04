package com.helpstudents.domain;

import com.helpstudents.entity.OrderEntity;
import com.helpstudents.entity.WorkerEntity;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class PricePropositionDTO {
    private Long id;
    private BigDecimal price;

    private WorkerDTOForAll workerDTOForAll;

    private OrderDTO orderDTO;
}

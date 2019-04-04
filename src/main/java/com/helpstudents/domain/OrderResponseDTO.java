package com.helpstudents.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDTO {
    private Long id;
    private String description;
    private LocalDateTime dateCreate;
    private LocalDateTime dateMade;
    private String status;
    private BigDecimal price;
    private String file;
}

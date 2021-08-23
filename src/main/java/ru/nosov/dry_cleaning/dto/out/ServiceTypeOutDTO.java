package ru.nosov.dry_cleaning.dto.out;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ServiceTypeOutDTO {
    private Long id;
    private String type;
    private BigDecimal price;
    private Long orderId;
}

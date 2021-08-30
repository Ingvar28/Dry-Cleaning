package ru.nosov.dry_cleaning.dto.in;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ServiceTypeInDTO {
    private Long id;
    private String type;
    private BigDecimal price;
}

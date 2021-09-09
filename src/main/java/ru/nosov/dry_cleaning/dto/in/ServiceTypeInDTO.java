package ru.nosov.dry_cleaning.dto.in;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ServiceTypeInDTO extends AbstractInDTO {

    private String type;
    private BigDecimal price;
}

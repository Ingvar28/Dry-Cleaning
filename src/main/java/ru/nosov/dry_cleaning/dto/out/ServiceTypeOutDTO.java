package ru.nosov.dry_cleaning.dto.out;

import lombok.Data;
import lombok.NonNull;

import java.math.BigDecimal;

@Data
public class ServiceTypeOutDTO extends AbstractOutDTO {

    private String serviceType;
    private BigDecimal price;
}

package ru.nosov.dry_cleaning.dto.out;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ClothesCategoryOutDTO {
    private Long id;
    private String type;
    private String size;
    private BigDecimal price;

}

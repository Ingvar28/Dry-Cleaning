package ru.nosov.dry_cleaning.dto.in;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ClothesCategoryInDTO extends AbstractInDTO {

    private String category;
    private String size;
    private BigDecimal price;

}

package ru.nosov.dry_cleaning.dto.out;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ClothesCategoryOutDTO extends AbstractOutDTO {

    private String type;
    private String size;
    private BigDecimal price;

}

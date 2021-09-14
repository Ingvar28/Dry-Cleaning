package ru.nosov.dry_cleaning.dto.in;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
public class ClothesCategoryInDTO extends AbstractInDTO {

    @NotNull
    @NotBlank
    private String type;

    @NotNull
    @NotBlank
    private String size;

    @NotNull
    @NotBlank
    private BigDecimal price;

}

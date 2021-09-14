package ru.nosov.dry_cleaning.dto.out;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class ItemOutDTO extends AbstractOutDTO {

    @NotNull
    @Positive
    @JsonProperty("orders_id")
    private Long orderId;

    @NotNull
    @Positive
    @JsonProperty("clothes_category_id")
    private Long clothesCategoryId;

    private String material;
    private String wash;
    private String squeezeOut;
    private String dry;
    private String iron;
    private String white;
    private String dryCleaning;


}

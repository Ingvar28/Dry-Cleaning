package ru.nosov.dry_cleaning.dto.in;

import lombok.Data;

@Data
public class ItemInDTO {
    private Long id;
    private Long orderId;
    private Long clothesCategoryId;

    private String material;
    private String wash;
    private String squeezeOut;
    private String dry;
    private String iron;
    private String white;
    private String dryCleaning;


}

package ru.nosov.dry_cleaning.dto;

import lombok.Data;

@Data
public class OrderDTO {
    private Long id;
    private Long orderStatusId;
    private Long cashierId;
    private Long clientId;


}

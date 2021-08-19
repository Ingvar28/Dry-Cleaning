package ru.nosov.dry_cleaning.dto;

import lombok.Data;

@Data
public class CashierDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private Integer description;
}

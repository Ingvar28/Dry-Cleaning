package ru.nosov.dry_cleaning.dto.in;

import lombok.Data;

@Data
public class EmployerInDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String positionId;
    private String orderId;
    private String payment;
}

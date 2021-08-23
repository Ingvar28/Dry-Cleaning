package ru.nosov.dry_cleaning.dto.in;

import lombok.Data;

@Data
public class PaymentInDTO {
    private Long id;
    private String paymentMethod;
    private String status;
    private Long employeeId;


}

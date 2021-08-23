package ru.nosov.dry_cleaning.dto.out;

import lombok.Data;

@Data
public class PaymentOutDTO {
    private Long id;
    private String paymentMethod;
    private String status;
    private Long employeeId;


}

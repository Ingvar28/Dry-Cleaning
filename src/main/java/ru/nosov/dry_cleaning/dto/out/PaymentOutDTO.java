package ru.nosov.dry_cleaning.dto.out;

import lombok.Data;

@Data
public class PaymentOutDTO extends AbstractOutDTO {

    private String paymentMethod;
    private String status;
    private Long employeeId;


}

package ru.nosov.dry_cleaning.dto.in;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderInDTO extends AbstractInDTO {

    private LocalDateTime orderStartTime;
    private LocalDateTime orderEndTime;
    private Long clientId;
    private Long paymentId;
    private Long serviceId;
    private Long employeeId;
    private String orderStatus;


}

package ru.nosov.dry_cleaning.dto.out;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderOutDTO extends AbstractOutDTO {

    private LocalDateTime orderStartTime;
    private LocalDateTime orderEndTime;
    private Long clientId;
    private Long paymentId;
    private Long serviceId;
    private Long employeeId;
    private String orderStatus;


}

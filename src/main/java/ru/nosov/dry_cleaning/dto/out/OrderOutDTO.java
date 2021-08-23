package ru.nosov.dry_cleaning.dto.out;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderOutDTO {
    private Long id;
    private LocalDateTime orderStartTime;
    private LocalDateTime orderEndTime;
    private Long clientId;
    private Long paymentId;
    private Long item;
    private Long serviceId;
    private Long employeeId;
    private Long orderStatus;


}

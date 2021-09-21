package ru.nosov.dry_cleaning.dto.out;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderOutDTO extends AbstractOutDTO {

    private LocalDateTime orderStartTime;
    private LocalDateTime orderEndTime;
    private Long clientId;
    private Long paymentId;
    private Long serviceId;
    private Long employeeId;
    private String orderStatus;

    //TODO delete commented

//    private List<Long> itemIdList;


}

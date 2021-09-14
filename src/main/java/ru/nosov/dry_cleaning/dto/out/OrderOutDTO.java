package ru.nosov.dry_cleaning.dto.out;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import ru.nosov.dry_cleaning.entities.ItemEntity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderOutDTO extends AbstractOutDTO {

    private LocalDateTime orderStartTime;
    private LocalDateTime orderEndTime;

    @NotNull
    @Positive
    @JsonProperty("client_id")
    private Long clientId;

    @NotNull
    @Positive
    @JsonProperty("payment_id")
    private Long paymentId;

    private List<ItemEntity> items;

    @NotNull
    @Positive
    @JsonProperty("service_id")
    private Long serviceId;

    @NotNull
    @Positive
    @JsonProperty("employee_id")
    private Long employeeId;

    private String orderStatus;


}

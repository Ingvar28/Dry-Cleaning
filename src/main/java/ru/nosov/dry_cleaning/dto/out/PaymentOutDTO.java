package ru.nosov.dry_cleaning.dto.out;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class PaymentOutDTO extends AbstractOutDTO {

    private String paymentMethod;
    private String status;

    @NotNull
    @Positive
    @JsonProperty("employee_id")
    private Long employeeId;


}

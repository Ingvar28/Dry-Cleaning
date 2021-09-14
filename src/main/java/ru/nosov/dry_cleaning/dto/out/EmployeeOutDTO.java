package ru.nosov.dry_cleaning.dto.out;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class EmployeeOutDTO extends AbstractOutDTO {

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String phone;

    @NotNull
    @Positive
    @JsonProperty("position_id")
    private Long positionId;


}

package ru.nosov.dry_cleaning.dto.in;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class EmployeeInDTO extends AbstractInDTO {


    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String phone;

    @NotNull
    @Positive
    @JsonProperty("publishing_house_id")
    private Long positionId;
}

package ru.nosov.dry_cleaning.dto.out;

import lombok.Data;

@Data
public class EmployeeOutDTO extends AbstractOutDTO {

    private String firstName;
    private String lastName;
    private String phone;
    private Long positionId;
}

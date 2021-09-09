package ru.nosov.dry_cleaning.dto.in;

import lombok.Data;

@Data
public class EmployeeInDTO extends AbstractInDTO {

    private String firstName;
    private String lastName;
    private String phone;
    private Long positionId;
}

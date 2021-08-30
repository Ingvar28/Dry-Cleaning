package ru.nosov.dry_cleaning.dto.in;

import lombok.Data;

@Data
public class PositionInDTO {
    private Long id;
    private String jobTitle;
    private String duties;

}

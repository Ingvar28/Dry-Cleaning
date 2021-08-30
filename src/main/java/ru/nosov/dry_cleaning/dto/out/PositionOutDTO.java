package ru.nosov.dry_cleaning.dto.out;

import lombok.Data;

@Data
public class PositionOutDTO {
    private Long id;
    private String jobTitle;
    private String duties;

}

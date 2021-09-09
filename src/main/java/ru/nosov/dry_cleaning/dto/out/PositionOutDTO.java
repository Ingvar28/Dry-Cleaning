package ru.nosov.dry_cleaning.dto.out;

import lombok.Data;

@Data
public class PositionOutDTO extends AbstractOutDTO {

    private String jobTitle;
    private String duties;

}

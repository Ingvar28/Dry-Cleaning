package ru.nosov.dry_cleaning.dto.in;

import lombok.Data;

@Data
public class PositionInDTO extends AbstractInDTO {

    private String jobTitle;
    private String duties;

}

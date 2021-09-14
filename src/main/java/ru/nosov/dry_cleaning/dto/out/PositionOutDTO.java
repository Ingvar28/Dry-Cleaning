package ru.nosov.dry_cleaning.dto.out;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Set;

@Data
public class PositionOutDTO extends AbstractOutDTO {

    private String jobTitle;
    private String duties;



}

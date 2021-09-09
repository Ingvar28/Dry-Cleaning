package ru.nosov.dry_cleaning.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public interface AbstractDTO {
}

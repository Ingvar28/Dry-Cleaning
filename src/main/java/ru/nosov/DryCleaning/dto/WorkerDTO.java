package ru.nosov.DryCleaning.dto;

import lombok.Data;

@Data
public class WorkerDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private Integer description;
}

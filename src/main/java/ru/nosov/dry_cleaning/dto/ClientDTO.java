package ru.nosov.dry_cleaning.dto;

import lombok.Data;

@Data
public class ClientDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String clientGrade;
    private String description;
    private Long orderId;




}

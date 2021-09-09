package ru.nosov.dry_cleaning.dto.out;

import lombok.Data;

@Data
public class ClientOutDTO extends AbstractOutDTO {

    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String clientLevel;
    private String description;




}

package ru.nosov.dry_cleaning.dto.in;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientInDTO extends AbstractInDTO {

    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String clientLevel;
    private String description;




}

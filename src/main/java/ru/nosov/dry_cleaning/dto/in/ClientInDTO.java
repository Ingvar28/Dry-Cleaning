package ru.nosov.dry_cleaning.dto.in;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientInDTO extends AbstractInDTO {

    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String clientLevel;
    private String description;

    private List<Long> orderId;


}

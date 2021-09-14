package ru.nosov.dry_cleaning.dto.out;

import com.sun.istack.NotNull;
import lombok.Data;
import ru.nosov.dry_cleaning.entities.OrderEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
public class ClientOutDTO extends AbstractOutDTO {

    private String firstName;
    private String lastName;
    private String phone;

    @NotNull
    @NotBlank
    private String email;
    private String clientLevel;
    private String description;






}

package ru.nosov.dry_cleaning.dto.in;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.nosov.dry_cleaning.entities.OrderEntity;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientInDTO extends AbstractInDTO {

    private String firstName;
    private String lastName;
    private String phone;

    @NotNull
    @NotBlank
    private String email;
    private String clientLevel;
    private String description;


}

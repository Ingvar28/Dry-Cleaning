package ru.nosov.dry_cleaning.dto.in;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import ru.nosov.dry_cleaning.dto.AbstractDTO;

@SuperBuilder
@Setter
@Getter
@NoArgsConstructor
public abstract class AbstractInDTO implements AbstractDTO {
    private Long id;
}

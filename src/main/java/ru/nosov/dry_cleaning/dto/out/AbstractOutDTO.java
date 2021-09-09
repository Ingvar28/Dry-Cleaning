package ru.nosov.dry_cleaning.dto.out;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import ru.nosov.dry_cleaning.dto.AbstractDTO;

import javax.validation.constraints.Positive;

@SuperBuilder
@Setter
@Getter
@NoArgsConstructor
public class AbstractOutDTO implements AbstractDTO {
    @NonNull
    @Positive
    private Long id;
}

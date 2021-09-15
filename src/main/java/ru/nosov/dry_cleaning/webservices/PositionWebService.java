package ru.nosov.dry_cleaning.webservices;

import ru.nosov.dry_cleaning.dto.in.PositionInDTO;
import ru.nosov.dry_cleaning.dto.out.PositionOutDTO;

import javax.transaction.Transactional;
import java.util.List;

public interface PositionWebService {

    PositionOutDTO getById(Long id);

    List<PositionOutDTO> getAll();

    @Transactional
    PositionOutDTO create(PositionInDTO dto);

    @Transactional
    PositionOutDTO update(PositionInDTO dto);

    @Transactional
    void deleteById(Long id);
}

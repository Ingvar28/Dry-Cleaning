package ru.nosov.dry_cleaning.services;

import ru.nosov.dry_cleaning.dto.in.PositionInDTO;
import ru.nosov.dry_cleaning.dto.out.PositionOutDTO;
import ru.nosov.dry_cleaning.entities.PositionEntity;

import javax.transaction.Transactional;
import java.util.List;

public interface PositionService {

    @Transactional
    PositionEntity create(String jobTitle, String duties);

    @Transactional
    void deleteById(Long id);

    PositionEntity getById(Long id);

    List<PositionEntity> getAll();

    @Transactional
    PositionEntity update(Long id, String jobTitle, String duties);

    PositionInDTO toInDTO(PositionEntity positionEntity);

    PositionOutDTO toOutDTO(PositionEntity positionEntity);

}

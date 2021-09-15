package ru.nosov.dry_cleaning.services;

import ru.nosov.dry_cleaning.dto.in.ItemInDTO;
import ru.nosov.dry_cleaning.dto.in.PositionInDTO;
import ru.nosov.dry_cleaning.dto.out.PositionOutDTO;
import ru.nosov.dry_cleaning.entities.ItemEntity;
import ru.nosov.dry_cleaning.entities.PositionEntity;

import javax.transaction.Transactional;
import java.util.List;

public interface PositionService {

    @Transactional
    PositionEntity create(PositionInDTO dto);

    @Transactional
    void deleteById(Long id);

    PositionEntity getById(Long id);

    List<PositionEntity> getAll();

    @Transactional
    PositionEntity update(PositionInDTO dto);

    PositionInDTO toInDTO(PositionEntity positionEntity);
    PositionOutDTO toOutDTO(PositionEntity positionEntity);
    PositionEntity inDTOToEntity(PositionInDTO dto);

}

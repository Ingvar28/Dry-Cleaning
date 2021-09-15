package ru.nosov.dry_cleaning.services;

import ru.nosov.dry_cleaning.dto.in.ClothesCategoryInDTO;
import ru.nosov.dry_cleaning.dto.out.ClothesCategoryOutDTO;
import ru.nosov.dry_cleaning.entities.ClothesCategoryEntity;

import javax.transaction.Transactional;
import java.util.List;

public interface ClothesCategoryService {

    @Transactional
    ClothesCategoryEntity create(ClothesCategoryInDTO dto);

    @Transactional
    void deleteById(Long id);

    ClothesCategoryEntity getById(Long id);

    List<ClothesCategoryEntity> getAll();

    @Transactional
    ClothesCategoryEntity update(ClothesCategoryInDTO dto);

    ClothesCategoryInDTO toInDTO(ClothesCategoryEntity clothescategoryEntity);

    ClothesCategoryOutDTO toOutDTO(ClothesCategoryEntity clothescategoryEntity);

    ClothesCategoryEntity inDTOToEntity(ClothesCategoryInDTO dto);
}

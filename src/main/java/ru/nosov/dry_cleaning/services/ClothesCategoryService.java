package ru.nosov.dry_cleaning.services;

import ru.nosov.dry_cleaning.dto.in.ClothesCategoryInDTO;
import ru.nosov.dry_cleaning.dto.out.ClothesCategoryOutDTO;
import ru.nosov.dry_cleaning.entities.ClothesCategoryEntity;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

public interface ClothesCategoryService {

    @Transactional
    ClothesCategoryEntity create(String type,
                                 String size,
                                 BigDecimal price);

    @Transactional
    void deleteById(Long id);

    ClothesCategoryEntity getById(Long id);

    List<ClothesCategoryEntity> getAll();

    @Transactional
    ClothesCategoryEntity update(Long id,
                                 String type,
                                 String size,
                                 BigDecimal price);

    ClothesCategoryInDTO toInDTO(ClothesCategoryEntity clothescategoryEntity);

    ClothesCategoryOutDTO toOutDTO(ClothesCategoryEntity clothescategoryEntity);

}

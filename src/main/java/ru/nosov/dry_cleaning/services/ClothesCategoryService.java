package ru.nosov.dry_cleaning.services;

import ru.nosov.dry_cleaning.dto.in.ClothesCategoryInDTO;
import ru.nosov.dry_cleaning.dto.out.ClothesCategoryOutDTO;
import ru.nosov.dry_cleaning.entities.clothesCategoryEntity;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

public interface ClothesCategoryService {

    @Transactional
    clothesCategoryEntity create(String type,
                                 String size,
                                 BigDecimal price);

    @Transactional
    void deleteById(Long id);

    clothesCategoryEntity getById(Long id);

    List<clothesCategoryEntity> getAll();

    @Transactional
    clothesCategoryEntity update(Long id,
                                 String type,
                                 String size,
                                 BigDecimal price);

    ClothesCategoryInDTO toInDTO(clothesCategoryEntity clothescategoryEntity);

    ClothesCategoryOutDTO toOutDTO(clothesCategoryEntity clothescategoryEntity);

}

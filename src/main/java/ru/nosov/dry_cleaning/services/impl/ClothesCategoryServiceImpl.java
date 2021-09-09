package ru.nosov.dry_cleaning.services.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nosov.dry_cleaning.dto.in.ClothesCategoryInDTO;
import ru.nosov.dry_cleaning.dto.out.ClothesCategoryOutDTO;
import ru.nosov.dry_cleaning.entities.ClothesCategoryEntity;
import ru.nosov.dry_cleaning.exceptions.DryCleaningApiException;
import ru.nosov.dry_cleaning.repositories.ClothesCategoryRepository;
import ru.nosov.dry_cleaning.services.ClothesCategoryService;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
@RequiredArgsConstructor
public class ClothesCategoryServiceImpl implements ClothesCategoryService {

    private final ClothesCategoryRepository clothesCategoryRepository;
    private final ObjectMapper mapper;

    private static final String NO_SUCH_CLOTHES_CATEGORY = "There is no such Clothes Category!";
    private static final String DTO_MUST_NOT_BE_NULL_MESSAGE = "DTO must not be null!";

    @Transactional
    public ClothesCategoryEntity create(String type,
                                        String size,
                                        BigDecimal price) {
        ClothesCategoryEntity clothesCategoryEntity = new ClothesCategoryEntity();
        clothesCategoryEntity.setClothesCategory(type);
        clothesCategoryEntity.setSize(size);
        clothesCategoryEntity.setPrice(price);

        return clothesCategoryRepository.save(clothesCategoryEntity);
    }


    @Override
    public void deleteById(Long id) {
        log.debug(String.format("Deleting ClothesCategory by id %s.%n", id));
        if (!clothesCategoryRepository.existsById(id)) {
            throw new DryCleaningApiException(NO_SUCH_CLOTHES_CATEGORY);
        }
        clothesCategoryRepository.deleteById(id);
    }

    @Override
    public ClothesCategoryEntity getById(Long id) {
        log.debug(String.format("Getting ClothesCategory by id: %s.%n", id));
        Optional<ClothesCategoryEntity> clothesCategory = clothesCategoryRepository.findById(id);
        if (clothesCategory.isPresent()) {
            return clothesCategory.get();
        } else {
            throw new DryCleaningApiException(NO_SUCH_CLOTHES_CATEGORY);
        }
    }

    @Override
    public List<ClothesCategoryEntity> getAll() {
        log.debug(String.format("Getting all ClothesCategory's.%n"));
        return clothesCategoryRepository.findAll();
    }

    @Override
    public ClothesCategoryEntity update(Long id, String type, String size, BigDecimal price) {
        log.debug(String.format("Updating ClothesCategory: %s, %s, %s, %s", id, type, size, price));
        ClothesCategoryEntity clothesCategoryEntity = clothesCategoryRepository.findById(id).
                orElseThrow(() -> new DryCleaningApiException(NO_SUCH_CLOTHES_CATEGORY));
        clothesCategoryEntity.setClothesCategory(type);
        clothesCategoryEntity.setSize(size);
        clothesCategoryEntity.setPrice(price);
        return clothesCategoryRepository.save(clothesCategoryEntity);
    }

    @Override
    public ClothesCategoryInDTO toInDTO(ClothesCategoryEntity clothescategoryEntity) {
        return Optional.ofNullable(clothescategoryEntity)
                .map(ent -> mapper.convertValue(ent, ClothesCategoryInDTO.class))
                .orElseThrow(() -> new DryCleaningApiException(DTO_MUST_NOT_BE_NULL_MESSAGE));

    }

    @Override
    public ClothesCategoryOutDTO toOutDTO(ClothesCategoryEntity clothescategoryEntity) {
        return Optional.ofNullable(clothescategoryEntity)
                .map(ent -> mapper.convertValue(ent, ClothesCategoryOutDTO.class))
                .orElseThrow(() -> new DryCleaningApiException(DTO_MUST_NOT_BE_NULL_MESSAGE));
    }

}

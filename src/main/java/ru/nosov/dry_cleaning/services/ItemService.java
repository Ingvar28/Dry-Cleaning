package ru.nosov.dry_cleaning.services;

import ru.nosov.dry_cleaning.dto.in.ItemInDTO;
import ru.nosov.dry_cleaning.dto.out.ItemOutDTO;
import ru.nosov.dry_cleaning.entities.ItemEntity;

import javax.transaction.Transactional;
import java.util.List;

public interface ItemService {

    @Transactional
    ItemEntity create(Long orderId,
                      Long clothesCategoryId,
                      String material,
                      String wash,
                      String squeezeOut,
                      String dry,
                      String iron,
                      String white,
                      String dryCleaning);

    @Transactional
    void deleteById(Long id);

    ItemEntity getById(Long id);

    List<ItemEntity> getAll();

    @Transactional
    ItemEntity update(Long id, Long orderId,
                      Long clothesCategoryId,
                      String material,
                      String wash,
                      String squeezeOut,
                      String dry,
                      String iron,
                      String white,
                      String dryCleaning);

    ItemInDTO toInDTO(ItemEntity itemEntity);

    ItemOutDTO toOutDTO(ItemEntity itemEntity);

}

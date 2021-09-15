package ru.nosov.dry_cleaning.services;

import ru.nosov.dry_cleaning.dto.in.EmployeeInDTO;
import ru.nosov.dry_cleaning.dto.in.ItemInDTO;
import ru.nosov.dry_cleaning.dto.out.ItemOutDTO;
import ru.nosov.dry_cleaning.entities.EmployeeEntity;
import ru.nosov.dry_cleaning.entities.ItemEntity;

import javax.transaction.Transactional;
import java.util.List;

public interface ItemService {

    @Transactional
    ItemEntity create(ItemInDTO dto);

    @Transactional
    void deleteById(Long id);

    ItemEntity getById(Long id);

    List<ItemEntity> getAll();

    @Transactional
    ItemEntity update(ItemInDTO dto);

    ItemInDTO toInDTO(ItemEntity itemEntity);
    ItemOutDTO toOutDTO(ItemEntity itemEntity);
    ItemEntity inDTOToEntity(ItemInDTO dto);

}

package ru.nosov.dry_cleaning.services.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nosov.dry_cleaning.dto.in.ClientInDTO;
import ru.nosov.dry_cleaning.dto.in.ItemInDTO;
import ru.nosov.dry_cleaning.dto.out.ItemOutDTO;
import ru.nosov.dry_cleaning.entities.*;
import ru.nosov.dry_cleaning.exceptions.DryCleaningApiException;
import ru.nosov.dry_cleaning.repositories.ClothesCategoryRepository;
import ru.nosov.dry_cleaning.repositories.ItemRepository;
import ru.nosov.dry_cleaning.repositories.OrderRepository;
import ru.nosov.dry_cleaning.services.ItemService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;
    private final ClothesCategoryRepository clothesCategoryRepository;
    private final ObjectMapper mapper;

    private static final String THERE_IS_NO_SUCH_ITEM = "There is no such Item!";
    private static final String NO_SUCH_ENTITY = "There are no such entity ";
    private static final String DTO_MUST_NOT_BE_NULL_MESSAGE = "DTO must not be null!";

    @Transactional

    public ItemEntity create(ItemInDTO dto) {
        if (dto == null) {
            throw new DryCleaningApiException(DTO_MUST_NOT_BE_NULL_MESSAGE + dto);
        }

        OrderEntity orderEntity = orderRepository.findById(dto.getOrderId())
                .orElseThrow(() -> new DryCleaningApiException(
                        NO_SUCH_ENTITY + OrderEntity.class + " " + dto.getOrderId()));

        ClothesCategoryEntity clothesCategoryEntity = clothesCategoryRepository.findById(dto.getClothesCategoryId())
                .orElseThrow(() -> new DryCleaningApiException(
                        NO_SUCH_ENTITY + ClothesCategoryEntity.class + " " + dto.getClothesCategoryId()));

        ItemEntity entity = mapper.convertValue(dto, ItemEntity.class);
        entity.setOrder(orderEntity);
        entity.setClothesCategory(clothesCategoryEntity);

        return itemRepository.save(entity);
    }


    @Override
    public void deleteById(Long id) {
        log.debug(String.format("Deleting Item by id %s.%n", id));
        ItemEntity entity = itemRepository.findById(id)
                .orElseThrow(() -> new DryCleaningApiException(
                        NO_SUCH_ENTITY + ItemEntity.class + " " + id));

        entity.setActive(false);
        itemRepository.save(entity);
    }

    @Override
    public ItemEntity getById(Long id) {
        log.debug(String.format("Getting item by id: %s.%n", id));
        Optional<ItemEntity> item = itemRepository.findById(id);
        if (item.isPresent()) {
            return item.get();
        } else {
            throw new DryCleaningApiException(THERE_IS_NO_SUCH_ITEM);
        }
    }

    @Override
    public List<ItemEntity> getAll() {
        log.debug(String.format("Getting all Items.%n"));
        return itemRepository.findAll();
    }

    @Override
    public ItemEntity update(ItemInDTO dto) {
        if (dto == null) {
            throw new DryCleaningApiException(DTO_MUST_NOT_BE_NULL_MESSAGE + dto);
        }

        log.debug(String.format("Updating Item: %s",
                dto));

        itemRepository.findById(dto.getId())
                .orElseThrow(() -> new DryCleaningApiException(
                        NO_SUCH_ENTITY + ItemEntity.class + " " + dto.getId()));

        OrderEntity orderEntity = orderRepository.findById(dto.getOrderId())
                .orElseThrow(() -> new DryCleaningApiException(
                        NO_SUCH_ENTITY + OrderEntity.class + " " + dto.getOrderId()));

        ClothesCategoryEntity clothesCategoryEntity = clothesCategoryRepository.findById(dto.getClothesCategoryId())
                .orElseThrow(() -> new DryCleaningApiException(
                        NO_SUCH_ENTITY + ClothesCategoryEntity.class + " " + dto.getClothesCategoryId()));

        ItemEntity entity = mapper.convertValue(dto, ItemEntity.class);
        entity.setOrder(orderEntity);
        entity.setClothesCategory(clothesCategoryEntity);

        return itemRepository.save(entity);
    }

    @Override
    public ItemInDTO toInDTO(ItemEntity itemEntity) {
        return Optional.ofNullable(itemEntity)
                .map(ent -> mapper.convertValue(ent, ItemInDTO.class))
                .orElseThrow(() -> new DryCleaningApiException(DTO_MUST_NOT_BE_NULL_MESSAGE));

    }

    @Override
    public ItemOutDTO toOutDTO(ItemEntity itemEntity) {
        return Optional.ofNullable(itemEntity)
                .map(ent -> mapper.convertValue(ent, ItemOutDTO.class))
                .orElseThrow(() -> new DryCleaningApiException(DTO_MUST_NOT_BE_NULL_MESSAGE));
    }

    public ItemEntity inDTOToEntity(ItemInDTO dto) {

        return Optional.ofNullable(dto)
                .map(ent -> mapper.convertValue(ent, ItemEntity.class))
                .orElseThrow(() -> new DryCleaningApiException(DTO_MUST_NOT_BE_NULL_MESSAGE));

    }

}

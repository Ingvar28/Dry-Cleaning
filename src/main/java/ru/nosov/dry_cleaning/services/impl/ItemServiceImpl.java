package ru.nosov.dry_cleaning.services.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nosov.dry_cleaning.dto.in.ItemInDTO;
import ru.nosov.dry_cleaning.dto.out.ItemOutDTO;
import ru.nosov.dry_cleaning.entities.ItemEntity;
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
    private static final String THERE_IS_NO_SUCH_ORDER = "There is no such Order!";
    private static final String THERE_IS_NO_SUCH_CLOTHES_CATEGORY = "There is no such Clothes Category!";
    private static final String DTO_MUST_NOT_BE_NULL_MESSAGE = "DTO must not be null!";

    @Transactional

    public ItemEntity create(Long orderId, Long clothesCategoryId, String material,
                             String wash, String squeezeOut, String dry, String iron,
                             String white, String dryCleaning) {
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setOrder(orderRepository.findById(orderId).
                orElseThrow(() -> new DryCleaningApiException(THERE_IS_NO_SUCH_ORDER)));
        itemEntity.setClothesCategory(clothesCategoryRepository.findById(clothesCategoryId).
                orElseThrow(() -> new DryCleaningApiException(THERE_IS_NO_SUCH_CLOTHES_CATEGORY)));
        itemEntity.setMaterial(material);
        itemEntity.setWash(wash);
        itemEntity.setSqueezeOut(squeezeOut);
        itemEntity.setDry(dry);
        itemEntity.setIron(iron);
        itemEntity.setWhite(white);
        itemEntity.setDryCleaning(dryCleaning);

        return itemRepository.save(itemEntity);
    }


    @Override
    public void deleteById(Long id) {
        log.debug(String.format("Deleting Item by id %s.%n", id));
        if (!itemRepository.existsById(id)) {
            throw new DryCleaningApiException(THERE_IS_NO_SUCH_ITEM);
        }
        itemRepository.deleteById(id);
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
    public ItemEntity update(Long id, Long orderId, Long clothesCategoryId, String material,
                             String wash, String squeezeOut, String dry, String iron,
                             String white, String dryCleaning) {
        log.debug(String.format("Updating Item: %s, %s, %s, %s, %s, %s, %s, %s, %s, %s",
                id, orderId, clothesCategoryId, material,
                wash, squeezeOut, dry, iron,
                white, dryCleaning));
        ItemEntity itemEntity = itemRepository.findById(id).
                orElseThrow(() -> new DryCleaningApiException(THERE_IS_NO_SUCH_ITEM));
        itemEntity.setOrder(orderRepository.findById(orderId).
                orElseThrow(() -> new DryCleaningApiException(THERE_IS_NO_SUCH_ORDER)));
        itemEntity.setClothesCategory(clothesCategoryRepository.findById(clothesCategoryId).
                orElseThrow(() -> new DryCleaningApiException(THERE_IS_NO_SUCH_CLOTHES_CATEGORY)));
        itemEntity.setMaterial(material);
        itemEntity.setWash(wash);
        itemEntity.setSqueezeOut(squeezeOut);
        itemEntity.setDry(dry);
        itemEntity.setIron(iron);
        itemEntity.setWhite(white);
        itemEntity.setDryCleaning(dryCleaning);

        return itemRepository.save(itemEntity);
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

}

package ru.nosov.dry_cleaning.services;

import ru.nosov.dry_cleaning.dto.in.ItemInDTO;
import ru.nosov.dry_cleaning.dto.in.OrderInDTO;
import ru.nosov.dry_cleaning.dto.out.OrderOutDTO;
import ru.nosov.dry_cleaning.entities.ItemEntity;
import ru.nosov.dry_cleaning.entities.OrderEntity;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {

    @Transactional
    OrderEntity create(OrderInDTO dto);

    @Transactional
    void deleteById(Long id);

    OrderEntity getById(Long id);

    List<OrderEntity> getAll();

    @Transactional
    OrderEntity update(OrderInDTO dto);

    OrderInDTO toInDTO(OrderEntity orderEntity);
    OrderOutDTO toOutDTO(OrderEntity orderEntity);
    OrderEntity inDTOToEntity(OrderInDTO dto);

}

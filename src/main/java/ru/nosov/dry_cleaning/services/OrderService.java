package ru.nosov.dry_cleaning.services;

import ru.nosov.dry_cleaning.dto.in.OrderInDTO;
import ru.nosov.dry_cleaning.dto.out.OrderOutDTO;
import ru.nosov.dry_cleaning.entities.OrderEntity;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {

    @Transactional
    OrderEntity create(LocalDateTime orderStartTime, LocalDateTime orderEndTime,
                       Long clientId, Long paymentId,Long serviceId,
                       Long employeeId, String orderStatus);

    @Transactional
    void deleteById(Long id);

    OrderEntity getById(Long id);

    List<OrderEntity> getAll();

    @Transactional
    OrderEntity update(Long id, LocalDateTime orderEndTime,
                       Long employeeId, String orderStatus);

    OrderInDTO toInDTO(OrderEntity orderEntity);
    OrderOutDTO toOutDTO(OrderEntity orderEntity);

}

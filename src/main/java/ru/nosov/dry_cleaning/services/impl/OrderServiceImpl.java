package ru.nosov.dry_cleaning.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nosov.dry_cleaning.dto.in.ClientInDTO;
import ru.nosov.dry_cleaning.dto.in.OrderInDTO;
import ru.nosov.dry_cleaning.dto.out.OrderOutDTO;
import ru.nosov.dry_cleaning.entities.*;
import ru.nosov.dry_cleaning.exceptions.DryCleaningApiException;
import ru.nosov.dry_cleaning.repositories.*;
import ru.nosov.dry_cleaning.services.OrderService;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final PaymentRepository paymentRepository;
    private final ServiceTypeRepository serviceTypeRepository;
    private final EmployeeRepository employeeRepository;

    private final ObjectMapper mapper;


    private static final String NO_ORDER_MESSAGE = "There is no such Order!";
    private static final String DTO_MUST_NOT_BE_NULL_MESSAGE = "DTO must not be null!";

    @Transactional
    public OrderEntity create(LocalDateTime orderEndTime,
                              Long clientId, Long paymentId, Long serviceId,
                              Long employeeId, String orderStatus) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderEndTime(orderEndTime);
        ClientEntity clientEntity = clientRepository.findById(clientId).orElse(null);
        orderEntity.setClient(clientEntity);
        PaymentEntity payment = paymentRepository.findById(paymentId).orElse(null);
        orderEntity.setPayment(payment);
        //Для Связи OneToMany с itemEntity
//        ItemEntity List<ItemEntity> = itemRepository.findAllById()
//        orderEntity.setItems(item);
        ServiceTypeEntity serviceType = serviceTypeRepository.findById(serviceId).orElse(null);
        orderEntity.setService(serviceType);
        EmployeeEntity employee = employeeRepository.findById(employeeId).orElse(null);
        orderEntity.setEmployee(employee);
        orderEntity.setOrderStatus(orderStatus);
        return orderRepository.save(orderEntity);
    }

    @Override
    public void deleteById(Long id) {
        log.debug(String.format("Deleting order by id %s.%n", id));
        if (!orderRepository.existsById(id)) {
            throw new DryCleaningApiException(NO_ORDER_MESSAGE);
        }
        orderRepository.deleteById(id);
    }

    @Override
    public OrderEntity getById(Long id) {
        log.debug(String.format("Getting order by id: %s.%n", id));
        Optional<OrderEntity> order = orderRepository.findById(id);
        if (order.isPresent()) {
            return order.get();
        } else {
            throw new DryCleaningApiException(NO_ORDER_MESSAGE);
        }
    }

    @Override
    public List<OrderEntity> getAll() {
        log.debug(String.format("Getting all orders.%n"));
        return orderRepository.findAll();
    }

    @Override
    public OrderEntity update(Long id,LocalDateTime orderEndTime,
                              Long employeeId, String orderStatus) {
        log.debug(String.format("Updating order: %s, %s, %s",
                orderEndTime, employeeId, orderStatus));
        OrderEntity orderEntity = orderRepository.findById(id).orElseThrow(() -> new DryCleaningApiException(NO_ORDER_MESSAGE));
        orderEntity.setOrderEndTime(orderEndTime);
        EmployeeEntity employee = employeeRepository.findById(employeeId).orElse(null);
        orderEntity.setEmployee(employee);
        orderEntity.setOrderStatus(orderStatus);
        return orderRepository.save(orderEntity);
    }

    @Override
    public OrderInDTO toInDTO(OrderEntity orderEntity) {
        return Optional.ofNullable(orderEntity)
                .map(ent -> mapper.convertValue(ent, OrderInDTO.class))
                .orElseThrow(() -> new DryCleaningApiException(DTO_MUST_NOT_BE_NULL_MESSAGE));

    }

    @Override
    public OrderOutDTO toOutDTO(OrderEntity orderEntity) {
        return Optional.ofNullable(orderEntity)
                .map(ent -> mapper.convertValue(ent, OrderOutDTO.class))
                .orElseThrow(() -> new DryCleaningApiException(DTO_MUST_NOT_BE_NULL_MESSAGE));

    }
}

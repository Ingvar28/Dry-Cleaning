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


    private static final String THERE_IS_NO_SUCH_ORDER = "There is no such Order!";
    private static final String THERE_IS_NO_SUCH_CLIENT = "There is no such Client!";
    private static final String THERE_IS_NO_SUCH_PAYMENT = "There is no such Payment!";
    private static final String THERE_IS_NO_SUCH_SERVICE_TYPE = "There is no such Service Type!";
    private static final String THERE_IS_NO_SUCH_EMPLOYEE = "There is no such  Employee!";
    private static final String DTO_MUST_NOT_BE_NULL_MESSAGE = "DTO must not be null!";

    @Transactional
    public OrderEntity create(OrderInDTO dto) {
        return orderRepository.save(inDTOToEntity(dto));
    }

    @Override
    public void deleteById(Long id) {
        log.debug(String.format("Deleting order by id %s.%n", id));
        if (!orderRepository.existsById(id)) {
            throw new DryCleaningApiException(THERE_IS_NO_SUCH_ORDER);
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
            throw new DryCleaningApiException(THERE_IS_NO_SUCH_ORDER);
        }
    }

    @Override
    public List<OrderEntity> getAll() {
        log.debug(String.format("Getting all orders.%n"));
        return orderRepository.findAll();
    }

    @Override
    public OrderEntity update(OrderInDTO dto) {
        log.debug(String.format("Updating order: %s", dto.toString()));
        return orderRepository.save(inDTOToEntity(dto));
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

    public OrderEntity inDTOToEntity(OrderInDTO dto) {

        return Optional.ofNullable(dto)
                .map(ent -> mapper.convertValue(ent, OrderEntity.class))
                .orElseThrow(() -> new DryCleaningApiException(DTO_MUST_NOT_BE_NULL_MESSAGE));

    }
}

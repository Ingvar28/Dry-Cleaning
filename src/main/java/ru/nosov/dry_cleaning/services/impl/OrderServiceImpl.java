package ru.nosov.dry_cleaning.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nosov.dry_cleaning.dto.in.OrderInDTO;
import ru.nosov.dry_cleaning.dto.out.OrderOutDTO;
import ru.nosov.dry_cleaning.entities.*;
import ru.nosov.dry_cleaning.exceptions.DryCleaningApiException;
import ru.nosov.dry_cleaning.repositories.*;
import ru.nosov.dry_cleaning.services.OrderService;

import javax.transaction.Transactional;
import java.util.ArrayList;
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
    private final ItemRepository itemRepository;

    private final ObjectMapper mapper;


    private static final String THERE_IS_NO_SUCH_ORDER = "There is no such Order!";
    private static final String THERE_IS_NO_SUCH_CLIENT = "There is no such Client!";
    private static final String THERE_IS_NO_SUCH_PAYMENT = "There is no such Payment!";
    private static final String THERE_IS_NO_SUCH_SERVICE_TYPE = "There is no such Service Type!";
    private static final String THERE_IS_NO_SUCH_EMPLOYEE = "There is no such  Employee!";
    private static final String DTO_MUST_NOT_BE_NULL_MESSAGE = "DTO must not be null!";
    private static final String NO_SUCH_ENTITY = "There are no such entity ";

    @Transactional
    public OrderEntity create(OrderInDTO dto) {

        if (dto == null) {
            throw new DryCleaningApiException(DTO_MUST_NOT_BE_NULL_MESSAGE + dto);
        }

        ClientEntity clientEntity = clientRepository.findById(dto.getClientId())
                .orElseThrow(() -> new DryCleaningApiException(
                        NO_SUCH_ENTITY + ClientEntity.class + " " + dto.getClientId()));

        PaymentEntity paymentEntity = paymentRepository.findById(dto.getPaymentId())
                .orElseThrow(() -> new DryCleaningApiException(
                        NO_SUCH_ENTITY + PaymentEntity.class + " " + dto.getPaymentId()));


        ServiceTypeEntity serviceTypeEntity = serviceTypeRepository.findById(dto.getServiceId())
                .orElseThrow(() -> new DryCleaningApiException(
                        NO_SUCH_ENTITY + ServiceTypeEntity.class + " " + dto.getServiceId()));

        EmployeeEntity employeeEntity = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new DryCleaningApiException(
                        NO_SUCH_ENTITY + EmployeeEntity.class + " " + dto.getEmployeeId()));


        List<ItemEntity> itemEntityList = new ArrayList<>();
        for (Long itemId : dto.getItemIdList()) {
            ItemEntity itemEntity = itemRepository.findById(itemId)
                    .orElseThrow(() -> new DryCleaningApiException(
                            NO_SUCH_ENTITY + ItemEntity.class + " " + itemId));
        }

        OrderEntity entity = mapper.convertValue(dto, OrderEntity.class);
        entity.setClient(clientEntity);
        entity.setPayment(paymentEntity);
        entity.setService(serviceTypeEntity);
        entity.setEmployee(employeeEntity);
        entity.setItems(itemEntityList);

        return orderRepository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        log.debug(String.format("Deleting order by id %s.%n", id));
        OrderEntity entity = orderRepository.findById(id)
                .orElseThrow(() -> new DryCleaningApiException(
                        NO_SUCH_ENTITY + OrderEntity.class + " " + id));

        entity.setActive(false);
        orderRepository.save(entity);
//        orderRepository.deleteById(id);
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

        if (dto == null) {
            throw new DryCleaningApiException(DTO_MUST_NOT_BE_NULL_MESSAGE + dto);
        }

        log.debug(String.format("Updating order: %s", dto));

        orderRepository.findById(dto.getId())
                .orElseThrow(() -> new DryCleaningApiException(
                        NO_SUCH_ENTITY + OrderEntity.class + " " + dto.getId()));

        ClientEntity clientEntity = clientRepository.findById(dto.getClientId())
                .orElseThrow(() -> new DryCleaningApiException(
                        NO_SUCH_ENTITY + ClientEntity.class + " " + dto.getClientId()));

        PaymentEntity paymentEntity = paymentRepository.findById(dto.getPaymentId())
                .orElseThrow(() -> new DryCleaningApiException(
                        NO_SUCH_ENTITY + PaymentEntity.class + " " + dto.getPaymentId()));


        ServiceTypeEntity serviceTypeEntity = serviceTypeRepository.findById(dto.getServiceId())
                .orElseThrow(() -> new DryCleaningApiException(
                        NO_SUCH_ENTITY + ServiceTypeEntity.class + " " + dto.getServiceId()));

        EmployeeEntity employeeEntity = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new DryCleaningApiException(
                        NO_SUCH_ENTITY + EmployeeEntity.class + " " + dto.getEmployeeId()));


        List<ItemEntity> itemEntityList = new ArrayList<>();
        for (Long itemId : dto.getItemIdList()) {
            ItemEntity itemEntity = itemRepository.findById(itemId)
                    .orElseThrow(() -> new DryCleaningApiException(
                            NO_SUCH_ENTITY + ItemEntity.class + " " + itemId));
        }

        OrderEntity entity = mapper.convertValue(dto, OrderEntity.class);
        entity.setClient(clientEntity);
        entity.setPayment(paymentEntity);
        entity.setService(serviceTypeEntity);
        entity.setEmployee(employeeEntity);
        entity.setItems(itemEntityList);

        return orderRepository.save(entity);
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

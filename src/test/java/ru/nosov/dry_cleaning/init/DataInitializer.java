package ru.nosov.dry_cleaning.init;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nosov.dry_cleaning.entities.*;
import ru.nosov.dry_cleaning.services.*;

@Service
@RequiredArgsConstructor
@Slf4j
@Getter
public class DataInitializer {

    private final ClientService clientService;
    private final ClothesCategoryService clothesCategoryService;
    private final EmployeeService employeeService;
    private final ItemService itemService;
    private final OrderService orderService;
    private final PaymentService paymentService;
    private final PositionService positionService;
    private final ServiceTypeService serviceTypeService;

    private final ValidDTO validDTO;

    private ClientEntity clientEntity;
    private ClothesCategoryEntity clothesCategoryEntity;
    private EmployeeEntity employeeEntity;
    private ItemEntity itemEntity;
    private OrderEntity orderEntity;
    private PaymentEntity paymentEntity;
    private PositionEntity positionEntity;
    private ServiceTypeEntity serviceTypeEntity;


    public static final Long LEGAL_MAX_ID = 1L;
    public static final Long ILLEGAL_MAX_ID = 2L;



    public void initData() {
        initializeClient();
        initializeClothesCategory();
        initializePosition();
        initializeEmployee();
        initializePayment();
        initializeServiceType();
        initializeItem();
        initializeOrder();


    }

    private void initializeClient() {
        ClientEntity clientEntity = clientService.create(validDTO.getClientInDTO());
        log.info("Created client entity: {}", clientEntity);
        this.clientEntity = clientEntity;
    }

    private void initializeClothesCategory() {
        ClothesCategoryEntity clothesCategoryEntity = clothesCategoryService.create(validDTO.getClothesCategoryInDTO());
        log.info("Created ClothesCategory entity: {}", clothesCategoryEntity);
        this.clothesCategoryEntity = clothesCategoryEntity;
    }

    private void initializeEmployee() {
        EmployeeEntity employeeEntity = employeeService.create(validDTO.getEmployeeInDTO());
        log.info("Created Employee entity: {}", employeeEntity);
        this.employeeEntity = employeeEntity;
    }

    private void initializeItem() {
        ItemEntity itemEntity = itemService.create(validDTO.getItemInDTO());
        log.info("Created Item entity: {}", itemEntity);
        this.itemEntity = itemEntity;
    }

    private void initializeOrder() {
        OrderEntity orderEntity = orderService.create(validDTO.getOrderInDTO());
        log.info("Created Order entity: {}", orderEntity);
        this.orderEntity = orderEntity;
    }

    private void initializePayment() {
        PaymentEntity paymentEntity = paymentService.create(validDTO.getPaymentInDTO());
        log.info("Created Payment entity: {}", paymentEntity);
        this.paymentEntity = paymentEntity;
    }

    private void initializePosition() {
        PositionEntity positionEntity = positionService.create(validDTO.getPositionInDTO());
        log.info("Created Position entity: {}", positionEntity);
        this.positionEntity = positionEntity;
    }

    private void initializeServiceType() {
        ServiceTypeEntity serviceTypeEntity = serviceTypeService.create(validDTO.getServiceTypeInDTO());
        log.info("Created ServiceType entity: {}", serviceTypeEntity);
        this.serviceTypeEntity = serviceTypeEntity;
    }


}

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

//    private ClientEntity clientEntity = new ClientEntity();
//    private ClothesCategoryEntity clothesCategoryEntity = new ClothesCategoryEntity();
//    private EmployeeEntity employeeEntity = new EmployeeEntity();
//    private ItemEntity itemEntity = new ItemEntity();
//    private OrderEntity orderEntity = new OrderEntity();
//    private PaymentEntity paymentEntity = new PaymentEntity();
//    private PositionEntity positionEntity = new PositionEntity();
//    private ServiceTypeEntity serviceTypeEntity = new ServiceTypeEntity();


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
        ClientEntity entity = clientService.create(validDTO.getClientInDTO());
        log.info("Created client entity: {}", entity);
        this.clientEntity = entity;
    }

    private void initializeClothesCategory() {
        ClothesCategoryEntity entity = clothesCategoryService.create(validDTO.getClothesCategoryInDTO());
        log.info("Created ClothesCategory entity: {}", entity);
        this.clothesCategoryEntity = entity;
    }

    private void initializeEmployee() {
        EmployeeEntity entity = employeeService.create(validDTO.getEmployeeInDTO());
        log.info("Created Employee entity: {}", entity);
        this.employeeEntity = entity;
    }

    private void initializeItem() {
        ItemEntity entity = itemService.create(validDTO.getItemInDTO());
        log.info("Created Item entity: {}", entity);
        this.itemEntity = entity;
    }

    private void initializeOrder() {
        OrderEntity entity = orderService.create(validDTO.getOrderInDTO());
        log.info("Created Order entity: {}", entity);
        this.orderEntity = entity;
    }

    private void initializePayment() {
        PaymentEntity entity = paymentService.create(validDTO.getPaymentInDTO());
        log.info("Created Payment entity: {}", entity);
        this.paymentEntity = entity;
    }

    private void initializePosition() {
        PositionEntity entity = positionService.create(validDTO.getPositionInDTO());
        log.info("Created Position entity: {}", entity);
        this.positionEntity = entity;
    }

    private void initializeServiceType() {
        ServiceTypeEntity entity = serviceTypeService.create(validDTO.getServiceTypeInDTO());
        log.info("Created ServiceType entity: {}", entity);
        this.serviceTypeEntity = entity;
    }


}

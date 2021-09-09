package ru.nosov.dry_cleaning.init;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nosov.dry_cleaning.dto.in.ClientInDTO;
import ru.nosov.dry_cleaning.dto.in.OrderInDTO;
import ru.nosov.dry_cleaning.entities.*;
import ru.nosov.dry_cleaning.repositories.*;
import ru.nosov.dry_cleaning.services.*;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class DataInitializer {

    private final ClientService clientService;
    private final ClothesCategoryService clothesCategoryService;
    private final EmployeeService employeeService;
    private final ItemService itemService;
    private final OrderService orderService;
    private final PaymentService paymentService;
    private final PositionService positionService;
    private final ServiceTypeService serviceTypeService;

    @Resource
    private ClientRepository clientRepository;
    @Resource
    private ClothesCategoryRepository clothesCategoryRepository;
    @Resource
    private EmployeeRepository employeeRepository;
    @Resource
    private ItemRepository itemRepository;
    @Resource
    private OrderRepository orderRepository;
    @Resource
    private PaymentRepository paymentRepository;
    @Resource
    private PositionRepository positionRepository;
    @Resource
    private ServiceTypeRepository serviceTypeRepository;

    public static PodamFactory factory = new PodamFactoryImpl();

    public ClientEntity validClient = factory.manufacturePojo(ClientEntity.class);
    public ClothesCategoryEntity validClothesCategory = factory.manufacturePojo(ClothesCategoryEntity.class);
    public EmployeeEntity validEmployee = factory.manufacturePojo(EmployeeEntity.class);
    public ItemEntity validItem = factory.manufacturePojo(ItemEntity.class);
    public OrderEntity validOrder = factory.manufacturePojo(OrderEntity.class);
    public PaymentEntity validPayment = factory.manufacturePojo(PaymentEntity.class);
    public PositionEntity validPosition = factory.manufacturePojo(PositionEntity.class);
    public ServiceTypeEntity validServiceType = factory.manufacturePojo(ServiceTypeEntity.class);
    public static final Integer LEGAL_MAX_ID = 10;


    public void initializeData() {

        validClient.setFirstName("John");
        validClient.setLastName("Weak");
        validClient.setPhone("123456789");
        validClient.setEmail("JohnWeak@gmail.com");
        validClient.setClientLevel("Bronze");
        validClient.setDescription("Angry man");
        Set<OrderEntity> orders = new HashSet<>();
        validClient.setOrders(orders);
        ClientEntity clientSaved = clientRepository.save(validClient);
        Long clientSavedId = clientSaved.getId();


        validPosition.setDuties("Cashier and Cleaning worker ");
        validPosition.setJobTitle("Master");
        PositionEntity positionSaved = positionRepository.save(validPosition);
        Long positionSavedId = positionSaved.getId();

        validEmployee.setFirstName("Bruce");
        validEmployee.setLastName("Wain");
        validEmployee.setPhone("987654321");
        validEmployee.setPosition(positionSaved);
        EmployeeEntity employeeSaved = employeeRepository.save(validEmployee);
        Long employeeSavedId = employeeSaved.getId();


        validPayment.setPaymentMethod("Credit Card");
        validPayment.setStatus("Paid");
        PaymentEntity paymentSaved = paymentRepository.save(validPayment);
        Long paymentSavedId = paymentSaved.getId();

        validClothesCategory.setClothesCategory("Сoat");
        validClothesCategory.setPrice(BigDecimal.valueOf(500.00));
        validClothesCategory.setSize("More 50 cm");
        ClothesCategoryEntity clothesCategorySaved = clothesCategoryRepository.save(validClothesCategory);
        Long clothesCategorySavedId = clothesCategorySaved.getId();

        validServiceType.setPrice(BigDecimal.valueOf(1000.00));
        validServiceType.setServiceType("Dry Cleaning");
        ServiceTypeEntity serviceTypeSaved = serviceTypeRepository.save(validServiceType);
        Long serviceTypeSavedId = serviceTypeSaved.getId();

        validItem.setOrder(validOrder);
        validItem.setClothesCategory(clothesCategorySaved);
        validItem.setMaterial("Material");
        validItem.setDryCleaning("Accessed");
        validItem.setWash("30 degrees");
        ItemEntity itemSaved = itemRepository.save(validItem);
        Long itemSavedId = itemSaved.getId();


        validOrder.setOrderEndTime(LocalDateTime.now().plusDays(7));
        validOrder.setClient(clientSaved);
        validOrder.setPayment(paymentSaved);
        Set<ItemEntity> items = new HashSet<>();
        items.add(itemSaved);
        validOrder.setItems(items);
        OrderEntity orderSaved = orderRepository.save(validOrder);
        Long orderSavedId = orderSaved.getId();
    }

    //TODO finish PODAM
    public void initDataPodam() {
        initializeClient();
        initializeOrder();
    }

    private void initializeOrder() {
        OrderInDTO dto = factory.manufacturePojo(OrderInDTO.class);
        validOrder.setOrderEndTime(dto.getOrderEndTime());

    }

    private void initializeClient() {
        ClientInDTO dto = factory.manufacturePojo(ClientInDTO.class);
        for (int i = 0; i < LEGAL_MAX_ID; i++) {

            validClient = clientService.create(
                    dto.getFirstName(),
                    dto.getLastName(),
                    dto.getPhone(),
                    dto.getEmail(),
                    dto.getDescription(),
                    dto.getClientLevel()
            );
            log.info("Created for test: {}", validClient);
        }
    }


}

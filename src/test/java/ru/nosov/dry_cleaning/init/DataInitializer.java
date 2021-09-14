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

    //TODO remove
//    private final ClothesCategoryService clothesCategoryService;
//    private final EmployeeService employeeService;
//    private final ItemService itemService;
//    private final OrderService orderService;
//    private final PaymentService paymentService;
//    private final PositionService positionService;
//    private final ServiceTypeService serviceTypeService;

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

//      TODO remove
//    public ClientEntity validClient = factory.manufacturePojo(ClientEntity.class);
//    public ClothesCategoryEntity validClothesCategory = factory.manufacturePojo(ClothesCategoryEntity.class);
//    public EmployeeEntity validEmployee = factory.manufacturePojo(EmployeeEntity.class);
//    public ItemEntity validItem = factory.manufacturePojo(ItemEntity.class);
//    public OrderEntity validOrder = factory.manufacturePojo(OrderEntity.class);
//    public PaymentEntity validPayment = factory.manufacturePojo(PaymentEntity.class);
//    public PositionEntity validPosition = factory.manufacturePojo(PositionEntity.class);
//    public ServiceTypeEntity validServiceType = factory.manufacturePojo(ServiceTypeEntity.class);

    public ClientEntity clientEntity = new ClientEntity();
    public ClothesCategoryEntity clothesCategoryEntity = new ClothesCategoryEntity();
    public EmployeeEntity employeeEntity = new EmployeeEntity();
    public ItemEntity itemEntity =new ItemEntity();
    public OrderEntity orderEntity = new OrderEntity();
    public PaymentEntity paymentEntity = new PaymentEntity();
    public PositionEntity positionEntity = new PositionEntity();
    public ServiceTypeEntity validServiceType = new ServiceTypeEntity();
    public static final Integer LEGAL_MAX_ID = 10;




    public void initializeData() {

        clientEntity.setFirstName("John");
        clientEntity.setLastName("Weak");
        clientEntity.setPhone("123456789");
        clientEntity.setEmail("JohnWeak@gmail.com");
        clientEntity.setClientLevel("Bronze");
        clientEntity.setDescription("Angry man");
        Set<OrderEntity> orders = new HashSet<>();
        clientEntity.setOrders(orders);
        clientRepository.save(clientEntity);



        positionEntity.setDuties("Cashier and Cleaning worker ");
        positionEntity.setJobTitle("Master");
       positionRepository.save(positionEntity);


        employeeEntity.setFirstName("Bruce");
        employeeEntity.setLastName("Wain");
        employeeEntity.setPhone("987654321");
        employeeEntity.setPosition(positionEntity);
        employeeRepository.save(employeeEntity);


        paymentEntity.setPaymentMethod("Credit Card");
        paymentEntity.setStatus("Paid");
        paymentEntity.setEmployee(employeeEntity);
        paymentRepository.save(paymentEntity);

        clothesCategoryEntity.setClothesCategory("Сoat");
        clothesCategoryEntity.setPrice(BigDecimal.valueOf(500.00));
        clothesCategoryEntity.setSize("More 50 cm");
        clothesCategoryRepository.save(clothesCategoryEntity);


        validServiceType.setPrice(BigDecimal.valueOf(1000.00));
        validServiceType.setServiceType("Dry Cleaning");
        serviceTypeRepository.save(validServiceType);


        itemEntity.setOrder(orderEntity);
        itemEntity.setClothesCategory(clothesCategoryEntity);
        itemEntity.setMaterial("Material");
        itemEntity.setDryCleaning("Accessed");
        itemEntity.setWash("30 degrees");
        itemEntity.setOrder(orderEntity);
        itemRepository.save(itemEntity);


        orderEntity.setOrderEndTime(LocalDateTime.now().plusDays(7));
        orderEntity.setClient(clientEntity);
        orderEntity.setPayment(paymentEntity);
        Set<ItemEntity> items = new HashSet<>();
        items.add(itemEntity);
        orderEntity.setItems(items);
        orderRepository.save(orderEntity);
    }

    //TODO finish PODAM
    public void initDataPodam() {
        initializeClient();
        initializeOrder();
    }

    private void initializeOrder() {
        OrderInDTO dto = factory.manufacturePojo(OrderInDTO.class);
        orderEntity.setOrderEndTime(dto.getOrderEndTime());

    }

    private void initializeClient() {
        ClientInDTO dto = factory.manufacturePojo(ClientInDTO.class);
        for (int i = 0; i < LEGAL_MAX_ID; i++) {

            clientEntity = clientService.create(
                    dto.getFirstName(),
                    dto.getLastName(),
                    dto.getPhone(),
                    dto.getEmail(),
                    dto.getDescription(),
                    dto.getClientLevel()
            );
            log.info("Created for test: {}", clientEntity);
        }
    }


}

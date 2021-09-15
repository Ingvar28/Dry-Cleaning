package ru.nosov.dry_cleaning.init;

import lombok.Getter;
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
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

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
    
//    
//
//    @Resource
//    private ClientRepository clientRepository;
//    @Resource
//    private ClothesCategoryRepository clothesCategoryRepository;
//    @Resource
//    private EmployeeRepository employeeRepository;
//    @Resource
//    private ItemRepository itemRepository;
//    @Resource
//    private OrderRepository orderRepository;
//    @Resource
//    private PaymentRepository paymentRepository;
//    @Resource
//    private PositionRepository positionRepository;
//    @Resource
//    private ServiceTypeRepository serviceTypeRepository;
//
//    public static PodamFactory factory = new PodamFactoryImpl();

//      TODO remove
//    public ClientEntity clientEntity = factory.manufacturePojo(ClientEntity.class);
//    public ClothesCategoryEntity clothesCategoryEntity = factory.manufacturePojo(ClothesCategoryEntity.class);
//    public EmployeeEntity employeeEntity = factory.manufacturePojo(EmployeeEntity.class);
//    public ItemEntity itemEntity = factory.manufacturePojo(ItemEntity.class);
//    public OrderEntity orderEntity = factory.manufacturePojo(OrderEntity.class);
//    public PaymentEntity paymentEntity = factory.manufacturePojo(PaymentEntity.class);
//    public PositionEntity positionEntity = factory.manufacturePojo(PositionEntity.class);
//    public ServiceTypeEntity serviceTypeEntity = factory.manufacturePojo(ServiceTypeEntity.class);

//    public ClientEntity clientEntity = new ClientEntity();
//    public ClothesCategoryEntity clothesCategoryEntity = new ClothesCategoryEntity();
//    public EmployeeEntity employeeEntity = new EmployeeEntity();
//    public ItemEntity itemEntity = new ItemEntity();
//    public OrderEntity orderEntity = new OrderEntity();
//    public PaymentEntity paymentEntity = new PaymentEntity();
//    public PositionEntity positionEntity = new PositionEntity();
//    public ServiceTypeEntity serviceTypeEntity = new public ClientEntity clientEntity = new ClientEntity();
//    TODO finis Initializer
//    private ClientEntity clientEntity;
//    private ClothesCategoryEntity clothesCategoryEntity;
//    private EmployeeEntity employeeEntity;
//    private ItemEntity itemEntity;
//    private OrderEntity orderEntity;
//    private PaymentEntity paymentEntity;
//    private PositionEntity positionEntity;
//    private ServiceTypeEntity serviceTypeEntity;
    private static final Integer LEGAL_MAX_ID = 10;

    
    //TODO finish PODAM
    public void initData() {
        initializeClient();

    }
    
    private void initializeClient() {

    }


}

package ru.nosov.dry_cleaning.init;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nosov.dry_cleaning.services.*;

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
}

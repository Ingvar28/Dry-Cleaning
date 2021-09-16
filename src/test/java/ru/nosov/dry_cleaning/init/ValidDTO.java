package ru.nosov.dry_cleaning.init;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nosov.dry_cleaning.dto.in.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Getter
@NoArgsConstructor
public class ValidDTO {

    private final ClientInDTO clientInDTO = initClientDto();
    private final ClothesCategoryInDTO clothesCategoryInDTO = initClothesCategoryDTO();
    private final EmployeeInDTO employeeInDTO = initEmployeeDTO();
    private final ItemInDTO itemInDTO = initItemDTO();
    private final OrderInDTO orderInDTO = initOrderDTO();
    private final PaymentInDTO paymentInDTO = initPaymentDTO();
    private final PositionInDTO positionInDTO = initPositionDTO();
    private final ServiceTypeInDTO serviceTypeInDTO = initServiceTypeDTO();


    private ClientInDTO initClientDto() {
        ClientInDTO dto = new ClientInDTO();
        dto.setId(1L);
        dto.setLastName("Weak");
        dto.setPhone("123456789");
        dto.setEmail("JohnWeak@gmail.com");
        dto.setClientLevel("Bronze");
        dto.setDescription("Angry man");
        dto.setFirstName("John");
        dto.setOrderId(List.of(1L));
        return dto;
    }

    private ClothesCategoryInDTO initClothesCategoryDTO() {
        ClothesCategoryInDTO dto = new ClothesCategoryInDTO();
        dto.setId(1L);
        dto.setCategory("Сoat");
        dto.setPrice(BigDecimal.valueOf(500.00));
        dto.setSize("More 50 cm");
        return dto;
    }

    private EmployeeInDTO initEmployeeDTO() {
        EmployeeInDTO dto = new EmployeeInDTO();
        dto.setId(1L);
        dto.setFirstName("Bruce");
        dto.setLastName("Wain");
        dto.setPhone("987654321");
        dto.setPositionId(1L);
        return dto;
    }

    private ItemInDTO initItemDTO() {
        ItemInDTO dto = new ItemInDTO();
        dto.setId(1L);
        dto.setOrderId(1L);
        dto.setClothesCategoryId(1L);
        dto.setMaterial("Material");
        dto.setDryCleaning("Accessed");
        dto.setWash("30 degrees");
        return dto;
    }

    private OrderInDTO initOrderDTO() {
        OrderInDTO dto = new OrderInDTO();
        dto.setId(1L);
        dto.setOrderStartTime(LocalDateTime.now());
        dto.setOrderEndTime(LocalDateTime.now().plusDays(7));
        dto.setClientId(1L);
        dto.setPaymentId(1L);
        dto.setItemIdList(List.of(1L));
        return dto;
    }

    private PaymentInDTO initPaymentDTO() {
        PaymentInDTO dto = new PaymentInDTO();
        dto.setId(1L);
        dto.setPaymentMethod("Credit Card");
        dto.setStatus("Paid");
        dto.setEmployeeId(1L);
        return dto;
    }

    private PositionInDTO initPositionDTO() {
        PositionInDTO dto = new PositionInDTO();
        dto.setId(1L);
        dto.setDuties("Cashier and Cleaning worker ");
        dto.setJobTitle("Master");
        return dto;
    }

    private ServiceTypeInDTO initServiceTypeDTO() {
        ServiceTypeInDTO dto = new ServiceTypeInDTO();
        dto.setId(1L);
        dto.setPrice(BigDecimal.valueOf(1000.00));
        dto.setServiceType("Dry Cleaning");
        return dto;
    }
}

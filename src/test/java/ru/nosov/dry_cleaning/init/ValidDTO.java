package ru.nosov.dry_cleaning.init;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nosov.dry_cleaning.dto.in.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
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
        dto.setId(Long.valueOf(1));
        dto.setLastName("Weak");
        dto.setPhone("123456789");
        dto.setEmail("JohnWeak@gmail.com");
        dto.setClientLevel("Bronze");
        dto.setDescription("Angry man");
        dto.setFirstName("John");
        return dto;
    }

    private ClothesCategoryInDTO initClothesCategoryDTO() {
        ClothesCategoryInDTO dto = new ClothesCategoryInDTO();
        dto.setId(Long.valueOf(1));
        dto.setClothesCategory("Сoat");
        dto.setPrice(BigDecimal.valueOf(500.00));
        dto.setSize("More 50 cm");
        return dto;
    }

    private EmployeeInDTO initEmployeeDTO() {
        EmployeeInDTO dto = new EmployeeInDTO();
        dto.setId(Long.valueOf(1));
        dto.setFirstName("Bruce");
        dto.setLastName("Wain");
        dto.setPhone("987654321");
        return dto;
    }

    private ItemInDTO initItemDTO() {
        ArtistInDTO dto = new ArtistInDTO();
        dto.setName("Slipknot");
        dto.setSubgenreIdList(List.of(1));
        return dto;
    }

    private OrderInDTO initOrderDTO() {
        VenueInDTO dto = new VenueInDTO();
        dto.setName("Aurora Concert Hall");
        dto.setCity("Saint-Petersburg");
        dto.setDescription("Современный клуб и концертный зал, где выступают российские и зарубежные рок-артисты");
        dto.setStreetAddress("Пироговская наб., 5/2");
        dto.setContacts("Телефон: 8 (812) 907-19-17");
        return dto;
    }

    private PaymentInDTO initPaymentDTO() {
        PaymentInDTO dto = new PaymentInDTO();
        dto.setId(Long.valueOf(1));
        dto.setPaymentMethod("Credit Card");
        dto.setStatus("Paid");
        return dto;
    }

    private PositionInDTO initPositionDTO() {
        PositionInDTO dto = new PositionInDTO();
        dto.setId(Long.valueOf(1));
        dto.setDuties("Cashier and Cleaning worker ");
        dto.setJobTitle("Master");
        return dto;
    }

    private ServiceTypeInDTO initServiceTypeDTO() {
        PurchaseInDTO dto = new PurchaseInDTO();
        dto.setEventId(1);
        dto.setTicketIdList(List.of(1));
        dto.setUserId(1);
        return dto;
    }
}

package ru.nosov.dry_cleaning.webservices;

import ru.nosov.dry_cleaning.dto.in.OrderInDTO;
import ru.nosov.dry_cleaning.dto.out.OrderOutDTO;

import javax.transaction.Transactional;
import java.util.List;

public interface OrderWebService {

    OrderOutDTO getById(Long id);

    List<OrderOutDTO> getAll();

    @Transactional
    OrderOutDTO create(OrderInDTO dto);

    @Transactional
    OrderOutDTO update(OrderInDTO dto);

    @Transactional
    void deleteById(Long id);
}

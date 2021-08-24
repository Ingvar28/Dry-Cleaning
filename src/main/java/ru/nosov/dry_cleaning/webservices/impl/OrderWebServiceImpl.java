package ru.nosov.dry_cleaning.webservices.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nosov.dry_cleaning.dto.in.OrderInDTO;
import ru.nosov.dry_cleaning.dto.out.OrderOutDTO;
import ru.nosov.dry_cleaning.services.OrderService;
import ru.nosov.dry_cleaning.webservices.OrderWebService;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderWebServiceImpl implements OrderWebService {

    private final OrderService service;

    @Override
    public OrderOutDTO getById(Long id) {
        return service.toOutDTO(service.getById(id));
    }

    @Override
    public List<OrderOutDTO> getAll() {
        return service.getAll().stream()
                .map(service::toOutDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OrderOutDTO create(OrderInDTO dto) {
        return service.toOutDTO(service.create(
                dto.getOrderStartTime(),
                dto.getOrderEndTime(),
                dto.getClientId(),
                dto.getPaymentId(),
                dto.getServiceId(),
                dto.getEmployeeId(),
                dto.getOrderStatus()
        ));
    }

    @Override
    public OrderOutDTO update(OrderInDTO dto) {
        return service.toOutDTO(service.update(
                dto.getId(),
                dto.getOrderEndTime(),
                dto.getEmployeeId(),
                dto.getOrderStatus()
        ));
    }

    @Override
    public void delete(Long id) { service.deleteById(id); }
}

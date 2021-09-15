package ru.nosov.dry_cleaning.webservices.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nosov.dry_cleaning.dto.in.PaymentInDTO;
import ru.nosov.dry_cleaning.dto.out.PaymentOutDTO;
import ru.nosov.dry_cleaning.services.PaymentService;
import ru.nosov.dry_cleaning.webservices.PaymentWebService;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentWebServiceImpl implements PaymentWebService {

    private final PaymentService service;

    @Override
    public PaymentOutDTO getById(Long id) {
        return service.toOutDTO(service.getById(id));
    }

    @Override
    public List<PaymentOutDTO> getAll() {
        return service.getAll().stream()
                .map(service::toOutDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PaymentOutDTO create(PaymentInDTO dto) {
        return service.toOutDTO(service.create(dto));
    }

    @Override
    public PaymentOutDTO update(PaymentInDTO dto) {
        return service.toOutDTO(service.update(dto));
    }

    @Override
    public void deleteById(Long id) {
        service.deleteById(id);
    }
}

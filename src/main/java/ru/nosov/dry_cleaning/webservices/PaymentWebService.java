package ru.nosov.dry_cleaning.webservices;

import ru.nosov.dry_cleaning.dto.in.PaymentInDTO;
import ru.nosov.dry_cleaning.dto.out.PaymentOutDTO;

import javax.transaction.Transactional;
import java.util.List;

public interface PaymentWebService {

    PaymentOutDTO getById(Long id);

    List<PaymentOutDTO> getAll();

    @Transactional
    PaymentOutDTO create(PaymentInDTO dto);

    @Transactional
    PaymentOutDTO update(PaymentInDTO dto);

    @Transactional
    void deleteById(Long id);
}

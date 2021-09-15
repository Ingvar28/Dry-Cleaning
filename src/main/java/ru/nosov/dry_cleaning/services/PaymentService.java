package ru.nosov.dry_cleaning.services;

import ru.nosov.dry_cleaning.dto.in.PaymentInDTO;
import ru.nosov.dry_cleaning.dto.out.PaymentOutDTO;
import ru.nosov.dry_cleaning.entities.PaymentEntity;

import javax.transaction.Transactional;
import java.util.List;

public interface PaymentService {

    @Transactional
    PaymentEntity create(PaymentInDTO dto);

    @Transactional
    void deleteById(Long id);

    PaymentEntity getById(Long id);

    List<PaymentEntity> getAll();

    @Transactional
    PaymentEntity update(PaymentInDTO dto);

    PaymentInDTO toInDTO(PaymentEntity paymentEntity);

    PaymentOutDTO toOutDTO(PaymentEntity paymentEntity);

    PaymentEntity inDTOToEntity(PaymentInDTO dto);

}

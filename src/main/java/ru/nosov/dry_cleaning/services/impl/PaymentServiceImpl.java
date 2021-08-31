package ru.nosov.dry_cleaning.services.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nosov.dry_cleaning.dto.in.PaymentInDTO;
import ru.nosov.dry_cleaning.dto.out.PaymentOutDTO;
import ru.nosov.dry_cleaning.entities.PaymentEntity;
import ru.nosov.dry_cleaning.exceptions.DryCleaningApiException;
import ru.nosov.dry_cleaning.repositories.EmployeeRepository;
import ru.nosov.dry_cleaning.repositories.PaymentRepository;
import ru.nosov.dry_cleaning.services.PaymentService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final EmployeeRepository employeeRepository;
    private final ObjectMapper mapper;

    private static final String THERE_IS_NO_SUCH_PAYMENT = "There is no such Payment!";
    private static final String THERE_IS_NO_SUCH_EMPLOYEE = "There is no such Employee!";
    private static final String DTO_MUST_NOT_BE_NULL_MESSAGE = "DTO must not be null!";

    @Transactional
    public PaymentEntity create(String paymentMethod, String status, Long employeeId) {
        PaymentEntity paymentEntity = new PaymentEntity();
        paymentEntity.setPaymentMethod(paymentMethod);
        paymentEntity.setStatus(status);
        paymentEntity.setEmployee(employeeRepository.findById(employeeId).
                orElseThrow(() -> new DryCleaningApiException(THERE_IS_NO_SUCH_EMPLOYEE)));

        return paymentRepository.save(paymentEntity);
    }


    @Override
    public void deleteById(Long id) {
        log.debug(String.format("Deleting Payment by id %s.%n", id));
        if (!paymentRepository.existsById(id)) {
            throw new DryCleaningApiException(THERE_IS_NO_SUCH_PAYMENT);
        }
        paymentRepository.deleteById(id);
    }

    @Override
    public PaymentEntity getById(Long id) {
        log.debug(String.format("Getting payment by id: %s.%n", id));
        Optional<PaymentEntity> payment = paymentRepository.findById(id);
        if (payment.isPresent()) {
            return payment.get();
        } else {
            throw new DryCleaningApiException(THERE_IS_NO_SUCH_PAYMENT);
        }
    }

    @Override
    public List<PaymentEntity> getAll() {
        log.debug(String.format("Getting all Payments.%n"));
        return paymentRepository.findAll();
    }

    @Override
    public PaymentEntity update(Long id, String paymentMethod, String status, Long employeeId) {
        log.debug(String.format("Updating Payment: %s, %s, %s, %s", id, paymentMethod, status, employeeId));
        PaymentEntity paymentEntity = paymentRepository.findById(id).
                orElseThrow(() -> new DryCleaningApiException(THERE_IS_NO_SUCH_EMPLOYEE));
        paymentEntity.setPaymentMethod(paymentMethod);
        paymentEntity.setStatus(status);
        paymentEntity.setEmployee(employeeRepository.findById(employeeId).
                orElseThrow(() -> new DryCleaningApiException(THERE_IS_NO_SUCH_EMPLOYEE)));
        return paymentRepository.save(paymentEntity);
    }

    @Override
    public PaymentInDTO toInDTO(PaymentEntity paymentEntity) {
        return Optional.ofNullable(paymentEntity)
                .map(ent -> mapper.convertValue(ent, PaymentInDTO.class))
                .orElseThrow(() -> new DryCleaningApiException(DTO_MUST_NOT_BE_NULL_MESSAGE));

    }

    @Override
    public PaymentOutDTO toOutDTO(PaymentEntity paymentEntity) {
        return Optional.ofNullable(paymentEntity)
                .map(ent -> mapper.convertValue(ent, PaymentOutDTO.class))
                .orElseThrow(() -> new DryCleaningApiException(DTO_MUST_NOT_BE_NULL_MESSAGE));
    }

}

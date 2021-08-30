package ru.nosov.dry_cleaning.services;

import ru.nosov.dry_cleaning.dto.in.ServiceTypeInDTO;
import ru.nosov.dry_cleaning.dto.out.ServiceTypeOutDTO;
import ru.nosov.dry_cleaning.entities.ServiceTypeEntity;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

public interface ServiceTypeService {

    @Transactional
    ServiceTypeEntity create(String type, BigDecimal price);

    @Transactional
    void deleteById(Long id);

    ServiceTypeEntity getById(Long id);

    List<ServiceTypeEntity> getAll();

    @Transactional
    ServiceTypeEntity update(Long id, String type, BigDecimal price);

    ServiceTypeInDTO toInDTO(ServiceTypeEntity serviceTypeEntity);

    ServiceTypeOutDTO toOutDTO(ServiceTypeEntity serviceTypeEntity);

}

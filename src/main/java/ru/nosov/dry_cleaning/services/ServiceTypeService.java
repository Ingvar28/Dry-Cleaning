package ru.nosov.dry_cleaning.services;

import ru.nosov.dry_cleaning.dto.in.ServiceTypeInDTO;
import ru.nosov.dry_cleaning.dto.out.ServiceTypeOutDTO;
import ru.nosov.dry_cleaning.entities.ServiceTypeEntity;

import javax.transaction.Transactional;
import java.util.List;

public interface ServiceTypeService {

    @Transactional
    ServiceTypeEntity create(ServiceTypeInDTO dto);

    @Transactional
    void deleteById(Long id);

    ServiceTypeEntity getById(Long id);

    List<ServiceTypeEntity> getAll();

    @Transactional
    ServiceTypeEntity update(ServiceTypeInDTO dto);

    ServiceTypeInDTO toInDTO(ServiceTypeEntity serviceTypeEntity);
    ServiceTypeOutDTO toOutDTO(ServiceTypeEntity serviceTypeEntity);
    ServiceTypeEntity inDTOToEntity(ServiceTypeInDTO dto);


}

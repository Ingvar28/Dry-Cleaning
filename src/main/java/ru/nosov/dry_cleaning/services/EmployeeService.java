package ru.nosov.dry_cleaning.services;

import ru.nosov.dry_cleaning.dto.in.ClientInDTO;
import ru.nosov.dry_cleaning.dto.in.EmployeeInDTO;
import ru.nosov.dry_cleaning.dto.out.EmployeeOutDTO;
import ru.nosov.dry_cleaning.entities.ClientEntity;
import ru.nosov.dry_cleaning.entities.EmployeeEntity;

import javax.transaction.Transactional;
import java.util.List;

public interface EmployeeService {

    @Transactional
    EmployeeEntity create(EmployeeInDTO dto);

    @Transactional
    void deleteById(Long id);

    EmployeeEntity getById(Long id);

    List<EmployeeEntity> getAll();

    @Transactional
    EmployeeEntity update(EmployeeInDTO dto);

    EmployeeInDTO toInDTO(EmployeeEntity employeeEntity);
    EmployeeOutDTO toOutDTO(EmployeeEntity employeeEntity);
    EmployeeEntity inDTOToEntity(EmployeeInDTO dto);

}

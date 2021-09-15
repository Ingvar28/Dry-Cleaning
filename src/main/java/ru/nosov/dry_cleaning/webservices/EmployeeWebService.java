package ru.nosov.dry_cleaning.webservices;

import ru.nosov.dry_cleaning.dto.in.EmployeeInDTO;
import ru.nosov.dry_cleaning.dto.out.EmployeeOutDTO;

import javax.transaction.Transactional;
import java.util.List;

public interface EmployeeWebService {

    EmployeeOutDTO getById(Long id);

    List<EmployeeOutDTO> getAll();

    @Transactional
    EmployeeOutDTO create(EmployeeInDTO dto);

    @Transactional
    EmployeeOutDTO update(EmployeeInDTO dto);

    @Transactional
    void deleteById(Long id);
}

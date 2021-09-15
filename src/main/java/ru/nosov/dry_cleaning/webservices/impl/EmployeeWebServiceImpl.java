package ru.nosov.dry_cleaning.webservices.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nosov.dry_cleaning.dto.in.EmployeeInDTO;
import ru.nosov.dry_cleaning.dto.out.EmployeeOutDTO;
import ru.nosov.dry_cleaning.services.EmployeeService;
import ru.nosov.dry_cleaning.webservices.EmployeeWebService;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeWebServiceImpl implements EmployeeWebService {

    private final EmployeeService service;

    @Override
    public EmployeeOutDTO getById(Long id) {
        return service.toOutDTO(service.getById(id));
    }

    @Override
    public List<EmployeeOutDTO> getAll() {
        return service.getAll().stream()
                .map(service::toOutDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeOutDTO create(EmployeeInDTO dto) {
        return service.toOutDTO(service.create(dto));
    }

    @Override
    public EmployeeOutDTO update(EmployeeInDTO dto) {
        return service.toOutDTO(service.update(dto));
    }

    @Override
    public void deleteById(Long id) {
        service.deleteById(id);
    }
}

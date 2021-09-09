package ru.nosov.dry_cleaning.webservices.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nosov.dry_cleaning.dto.in.ServiceTypeInDTO;
import ru.nosov.dry_cleaning.dto.out.ServiceTypeOutDTO;
import ru.nosov.dry_cleaning.services.ServiceTypeService;
import ru.nosov.dry_cleaning.webservices.ServiceTypeWebService;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ServiceTypeWebServiceImpl implements ServiceTypeWebService {

    private final ServiceTypeService service;

    @Override
    public ServiceTypeOutDTO getById(Long id) {
        return service.toOutDTO(service.getById(id));
    }

    @Override
    public List<ServiceTypeOutDTO> getAll() {
        return service.getAll().stream()
                .map(service::toOutDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ServiceTypeOutDTO create(ServiceTypeInDTO dto) {
        return service.toOutDTO(service.create(
                dto.getServiceType(),
                dto.getPrice()
        ));
    }

    @Override
    public ServiceTypeOutDTO update(ServiceTypeInDTO dto) {
        return service.toOutDTO(service.update(
                dto.getId(),
                dto.getServiceType(),
                dto.getPrice()
        ));
    }

    @Override
    public void delete(Long id) {
        service.deleteById(id);
    }
}

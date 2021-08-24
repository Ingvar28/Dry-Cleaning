package ru.nosov.dry_cleaning.webservices.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nosov.dry_cleaning.dto.in.ClientInDTO;
import ru.nosov.dry_cleaning.dto.out.ClientOutDTO;
import ru.nosov.dry_cleaning.services.ClientService;
import ru.nosov.dry_cleaning.webservices.ClientWebService;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientWebServiceImpl  implements ClientWebService {

    private final ClientService service;

    @Override
    public ClientOutDTO getById(Long id) { return service.toOutDTO(service.getById(id));
    }

    @Override
    public List<ClientOutDTO> getAll() {
        return service.getAll().stream()
                .map(service::toOutDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ClientOutDTO create(ClientInDTO dto) {
        return service.toOutDTO(service.create(
                dto.getFirstName(),
                dto.getLastName(),
                dto.getPhone(),
                dto.getEmail(),
                dto.getClientLevel(),
                dto.getDescription()
        ));
    }

    @Override
    public ClientOutDTO update(ClientInDTO dto) {
        return service.toOutDTO(service.update(
                dto.getId(),
                dto.getFirstName(),
                dto.getLastName(),
                dto.getPhone(),
                dto.getEmail(),
                dto.getClientLevel(),
                dto.getDescription()
        ));
    }

    @Override
    public void delete(Long id) { service.deleteById(id); }
}

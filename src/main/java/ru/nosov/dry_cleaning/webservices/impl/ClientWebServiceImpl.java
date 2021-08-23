package ru.nosov.dry_cleaning.webservices.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nosov.dry_cleaning.dto.in.ClientInDTO;
import ru.nosov.dry_cleaning.dto.out.ClientOutDTO;
import ru.nosov.dry_cleaning.services.ClientService;
import ru.nosov.dry_cleaning.webservices.ClientWebService;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientWebServiceImpl  implements ClientWebService {

    private final ClientService service;

    @Override
    public ClientOutDTO getById(Long id) {
        return null;
    }

    @Override
    public List<ClientOutDTO> getAll() {
        return null;
    }

    @Override
    public ClientOutDTO create(ClientInDTO dto) {
        return null;
    }

    @Override
    public ClientOutDTO update(ClientInDTO dto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}

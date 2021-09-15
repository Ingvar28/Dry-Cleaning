package ru.nosov.dry_cleaning.services.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nosov.dry_cleaning.dto.in.ClientInDTO;
import ru.nosov.dry_cleaning.dto.out.ClientOutDTO;
import ru.nosov.dry_cleaning.entities.ClientEntity;
import ru.nosov.dry_cleaning.entities.OrderEntity;
import ru.nosov.dry_cleaning.exceptions.DryCleaningApiException;
import ru.nosov.dry_cleaning.repositories.ClientRepository;
import ru.nosov.dry_cleaning.repositories.OrderRepository;
import ru.nosov.dry_cleaning.services.ClientService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {


    private final ClientRepository clientRepository;
    private final ObjectMapper mapper;

    private static final String THERE_IS_NO_SUCH_CLIENT = "There is no such Client!";
    private static final String DTO_MUST_NOT_BE_NULL_MESSAGE = "DTO must not be null!";

    @Transactional
    public ClientEntity create(ClientInDTO dto) {
        return clientRepository.save(inDTOToEntity(dto));
    }

    @Override
    public void deleteById(Long id) {
        log.debug(String.format("Deleting client by id %s.%n", id));
        if (!clientRepository.existsById(id)) {
            throw new DryCleaningApiException(THERE_IS_NO_SUCH_CLIENT);
        }
        clientRepository.deleteById(id);
    }

    @Override
    public ClientEntity getById(Long id) {
        log.debug(String.format("Getting client by id: %s.%n", id));
        Optional<ClientEntity> client = clientRepository.findById(id);
        if (client.isPresent()) {
            return client.get();
        } else {
            throw new DryCleaningApiException(THERE_IS_NO_SUCH_CLIENT);
        }
    }

    @Override
    public List<ClientEntity> getAll() {
        log.debug(String.format("Getting all clients.%n"));
        return clientRepository.findAll();
    }

    @Override
    public ClientEntity update(ClientInDTO dto) {
        log.debug(String.format("Updating client: %s",
                dto.toString()));

        return clientRepository.save(inDTOToEntity(dto));
    }

    @Override
    public ClientInDTO toInDTO(ClientEntity clientEntity) {
        return Optional.ofNullable(clientEntity)
                .map(ent -> mapper.convertValue(ent, ClientInDTO.class))
                .orElseThrow(() -> new DryCleaningApiException(DTO_MUST_NOT_BE_NULL_MESSAGE));

    }

    @Override
    public ClientOutDTO toOutDTO(ClientEntity clientEntity) {
        return Optional.ofNullable(clientEntity)
                .map(ent -> mapper.convertValue(ent, ClientOutDTO.class))
                .orElseThrow(() -> new DryCleaningApiException(DTO_MUST_NOT_BE_NULL_MESSAGE));
    }


    public ClientEntity inDTOToEntity(ClientInDTO dto) {

        return Optional.ofNullable(dto)
                .map(ent -> mapper.convertValue(ent, ClientEntity.class))
                .orElseThrow(() -> new DryCleaningApiException(DTO_MUST_NOT_BE_NULL_MESSAGE));

    }


}

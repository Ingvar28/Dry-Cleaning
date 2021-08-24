package ru.nosov.dry_cleaning.services.impl;


import ru.nosov.dry_cleaning.dto.in.ClientInDTO;
import ru.nosov.dry_cleaning.dto.out.ClientOutDTO;
import ru.nosov.dry_cleaning.entities.ClientEntity;
import ru.nosov.dry_cleaning.exceptions.DryCleaningApiException;
import ru.nosov.dry_cleaning.repositories.ClientRepository;
import ru.nosov.dry_cleaning.services.ClientService;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ObjectMapper mapper;

    private static final String NO_CLIENT_MESSAGE = "There is no such User!";
    private static final String DTO_MUST_NOT_BE_NULL_MESSAGE = "DTO must not be null!";

    @Transactional
    public ClientEntity create(String firstName, String lastName,
                               String phone, String email, String clientLevel,
                               String description) {
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setFirstName(firstName);
        clientEntity.setLastName(lastName);
        clientEntity.setPhone(phone);
        clientEntity.setEmail(email);
        clientEntity.setClientLevel(clientLevel);
        clientEntity.setDescription(description);
        return clientRepository.save(clientEntity);
    }

    @Override
    public void deleteById(Long id) {
        log.debug(String.format("Deleting client by id %s.%n", id));
        if (!clientRepository.existsById(id)) {
            throw new DryCleaningApiException(NO_CLIENT_MESSAGE);
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
            throw new DryCleaningApiException(NO_CLIENT_MESSAGE);
        }
    }

    @Override
    public List<ClientEntity> getAll() {
        log.debug(String.format("Getting all clients.%n"));
        return clientRepository.findAll();
    }

    @Override
    public ClientEntity update(Long id, String firstName, String lastName,
                               String phone, String email, String clientLevel,
                               String description) {
        log.debug(String.format("Updating client: %s, %s, %s, %s, %s, %s",firstName, lastName, phone, email, clientLevel, description));
        ClientEntity clientEntity = clientRepository.findById(id).orElseThrow(() -> new DryCleaningApiException(NO_CLIENT_MESSAGE));
        clientEntity.setFirstName(firstName);
        clientEntity.setLastName(lastName);
        clientEntity.setPhone(phone);
        clientEntity.setEmail(email);
        clientEntity.setClientLevel(clientLevel);
        clientEntity.setDescription(description);
        return clientRepository.save(clientEntity);
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

}

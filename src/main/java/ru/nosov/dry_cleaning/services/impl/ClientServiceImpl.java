package ru.nosov.dry_cleaning.services.impl;

import ru.nosov.dry_cleaning.entities.ClientEntity;
import ru.nosov.dry_cleaning.repositories.ClientRepository;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nosov.dry_cleaning.services.ClientService;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository repository;

    private static final String NO_USER_MESSAGE = "There is no such User!";
    private static final String DTO_MUST_NOT_BE_NULL_MESSAGE = "DTO must not be null!";

    @Transactional
    public ClientEntity create(String firstName, String lastName,
                               String phone, String email) {
        ClientEntity client = new ClientEntity();
        client.setFirstName(firstName);
        client.setLastName(lastName);
        client.setPhone(phone);
        client.setEmail(email);

        return repository.save(client);
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public ClientEntity getById(Long id) {
        return null;
    }

    @Override
    public List<ClientEntity> getAll() {
        return null;
    }

    @Override
    public ClientEntity update(String firstName, String lastName, String phone) {
        return null;
    }

    @Override
    public ClientEntity toInDTO(ClientEntity clientEntity) {
        return null;
    }

    @Override
    public ClientEntity toOutDTO(ClientEntity clientEntity) {
        return null;
    }

}

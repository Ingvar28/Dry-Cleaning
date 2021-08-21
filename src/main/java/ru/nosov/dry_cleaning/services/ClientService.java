package ru.nosov.dry_cleaning.services;

import ru.nosov.dry_cleaning.entities.ClientEntity;
import ru.nosov.dry_cleaning.repositories.ClientRepository;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@Slf4j
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository repository;

    private static final String NO_USER_MESSAGE = "There is no such User!";
    private static final String DTO_MUST_NOT_BE_NULL_MESSAGE = "DTO must not be null!";

    @Transactional
    public ClientEntity create(String firstName, String lastName,
                               String phone, String clientGrade, String description, Long orderId) {
        ClientEntity client = new ClientEntity();
        client.setFirstName(firstName);
        client.setLastName(lastName);
        client.setPhone(phone);
        client.setClientLevel(clientGrade);
        client.setDescription(description);

        return repository.save(client);
    }



}

package ru.nosov.dry_cleaning.services;
import ru.nosov.dry_cleaning.dto.in.ClientInDTO;
import ru.nosov.dry_cleaning.dto.out.ClientOutDTO;
import ru.nosov.dry_cleaning.entities.ClientEntity;

import javax.transaction.Transactional;
import java.util.List;

public interface ClientService {

    @Transactional
    ClientEntity create(String firstName, String lastName,
                        String phone, String email, String clientLevel,
                        String description);

    @Transactional
    void deleteById(Long id);

    ClientEntity getById(Long id);

    List<ClientEntity> getAll();

    @Transactional
    ClientEntity update(Long id, String firstName, String lastName,
                        String phone, String email, String clientLevel,
                        String description);

    ClientInDTO toInDTO(ClientEntity clientEntity);
    ClientOutDTO toOutDTO(ClientEntity clientEntity);

}

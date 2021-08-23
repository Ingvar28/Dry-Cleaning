package ru.nosov.dry_cleaning.services;
import ru.nosov.dry_cleaning.entities.ClientEntity;

import javax.transaction.Transactional;
import java.util.List;

public interface ClientService {

    @Transactional
    ClientEntity create(String firstName,String lastName,String phone, String email);

    @Transactional
    void deleteById(Long id);

    ClientEntity getById(Long id);

    List<ClientEntity> getAll();

    @Transactional
    ClientEntity update(String firstName,String lastName,String phone);

    ClientEntity toInDTO(ClientEntity clientEntity);
    ClientEntity toOutDTO(ClientEntity clientEntity);

}

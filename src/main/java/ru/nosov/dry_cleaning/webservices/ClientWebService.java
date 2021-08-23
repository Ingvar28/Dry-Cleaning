package ru.nosov.dry_cleaning.webservices;

import org.springframework.stereotype.Service;
import ru.nosov.dry_cleaning.dto.in.ClientInDTO;
import ru.nosov.dry_cleaning.dto.out.ClientOutDTO;

import javax.transaction.Transactional;
import java.util.List;

@Service
public interface ClientWebService {
    ClientOutDTO getById(Long id);

    List<ClientOutDTO> getAll();

    @Transactional
    ClientOutDTO create(ClientInDTO dto);

    @Transactional
    ClientOutDTO update(ClientInDTO dto);

    @Transactional
    void delete(Long id);
}

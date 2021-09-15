package ru.nosov.dry_cleaning.webservices;

import ru.nosov.dry_cleaning.dto.in.ServiceTypeInDTO;
import ru.nosov.dry_cleaning.dto.out.ServiceTypeOutDTO;

import javax.transaction.Transactional;
import java.util.List;

public interface ServiceTypeWebService {

    ServiceTypeOutDTO getById(Long id);

    List<ServiceTypeOutDTO> getAll();

    @Transactional
    ServiceTypeOutDTO create(ServiceTypeInDTO dto);

    @Transactional
    ServiceTypeOutDTO update(ServiceTypeInDTO dto);

    @Transactional
    void deleteById(Long id);
}

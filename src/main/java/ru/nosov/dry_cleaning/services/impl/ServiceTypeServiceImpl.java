package ru.nosov.dry_cleaning.services.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nosov.dry_cleaning.dto.in.ClientInDTO;
import ru.nosov.dry_cleaning.dto.in.ServiceTypeInDTO;
import ru.nosov.dry_cleaning.dto.out.ServiceTypeOutDTO;
import ru.nosov.dry_cleaning.entities.ClientEntity;
import ru.nosov.dry_cleaning.entities.ServiceTypeEntity;
import ru.nosov.dry_cleaning.exceptions.DryCleaningApiException;
import ru.nosov.dry_cleaning.repositories.ServiceTypeRepository;
import ru.nosov.dry_cleaning.services.ServiceTypeService;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
@RequiredArgsConstructor
public class ServiceTypeServiceImpl implements ServiceTypeService {

    private final ServiceTypeRepository serviceTypeRepository;
    private final ObjectMapper mapper;

    private static final String THERE_IS_NO_SUCH_SERVICE = "There is no such Service!";
    private static final String DTO_MUST_NOT_BE_NULL_MESSAGE = "DTO must not be null!";

    @Transactional
    public ServiceTypeEntity create(ServiceTypeInDTO dto) {
        return serviceTypeRepository.save(inDTOToEntity(dto));
    }


    @Override
    public void deleteById(Long id) {
        log.debug(String.format("Deleting ServiceType by id %s.%n", id));
        if (!serviceTypeRepository.existsById(id)) {
            throw new DryCleaningApiException(THERE_IS_NO_SUCH_SERVICE);
        }
        serviceTypeRepository.deleteById(id);
    }

    @Override
    public ServiceTypeEntity getById(Long id) {
        log.debug(String.format("Getting serviceType by id: %s.%n", id));
        Optional<ServiceTypeEntity> serviceType = serviceTypeRepository.findById(id);
        if (serviceType.isPresent()) {
            return serviceType.get();
        } else {
            throw new DryCleaningApiException(THERE_IS_NO_SUCH_SERVICE);
        }
    }

    @Override
    public ServiceTypeEntity update(ServiceTypeInDTO dto) {
        log.debug(String.format("Updating ServiceType: %s", dto.toString()));

        return serviceTypeRepository.save(inDTOToEntity(dto));
    }

    @Override
    public ServiceTypeInDTO toInDTO(ServiceTypeEntity serviceTypeEntity) {
        return Optional.ofNullable(serviceTypeEntity)
                .map(ent -> mapper.convertValue(ent, ServiceTypeInDTO.class))
                .orElseThrow(() -> new DryCleaningApiException(DTO_MUST_NOT_BE_NULL_MESSAGE));

    }

    @Override
    public List<ServiceTypeEntity> getAll() {
        log.debug(String.format("Getting all ServiceTypes.%n"));
        return serviceTypeRepository.findAll();
    }

    @Override
    public ServiceTypeOutDTO toOutDTO(ServiceTypeEntity serviceTypeEntity) {
        return Optional.ofNullable(serviceTypeEntity)
                .map(ent -> mapper.convertValue(ent, ServiceTypeOutDTO.class))
                .orElseThrow(() -> new DryCleaningApiException(DTO_MUST_NOT_BE_NULL_MESSAGE));
    }


    public ServiceTypeEntity inDTOToEntity(ServiceTypeInDTO dto) {

        return Optional.ofNullable(dto)
                .map(ent -> mapper.convertValue(ent, ServiceTypeEntity.class))
                .orElseThrow(() -> new DryCleaningApiException(DTO_MUST_NOT_BE_NULL_MESSAGE));

    }

}

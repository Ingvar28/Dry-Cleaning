package ru.nosov.dry_cleaning.services.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nosov.dry_cleaning.dto.in.EmployeeInDTO;
import ru.nosov.dry_cleaning.dto.out.EmployeeOutDTO;
import ru.nosov.dry_cleaning.entities.ClientEntity;
import ru.nosov.dry_cleaning.entities.EmployeeEntity;
import ru.nosov.dry_cleaning.entities.PositionEntity;
import ru.nosov.dry_cleaning.exceptions.DryCleaningApiException;
import ru.nosov.dry_cleaning.repositories.EmployeeRepository;
import ru.nosov.dry_cleaning.repositories.PositionRepository;
import ru.nosov.dry_cleaning.services.EmployeeService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {


    private final EmployeeRepository employeeRepository;
    private final PositionRepository positionRepository;
    private final ObjectMapper mapper;

    private static final String NO_SUCH_ENTITY = "There are no such entity ";
    private static final String THERE_IS_NO_SUCH_EMPLOYEE = "There is no such Employee!";
    private static final String DTO_MUST_NOT_BE_NULL_MESSAGE = "DTO must not be null!";

    @Transactional
    public EmployeeEntity create(EmployeeInDTO dto) {
        if (dto == null) {
            throw new DryCleaningApiException(DTO_MUST_NOT_BE_NULL_MESSAGE + dto);
        }

        PositionEntity positionEntity = positionRepository.findById(dto.getPositionId())
                .orElseThrow(() -> new DryCleaningApiException(NO_SUCH_ENTITY + PositionEntity.class + " " + dto.getPositionId()));

        EmployeeEntity entity = mapper.convertValue(dto, EmployeeEntity.class);
        entity.setPosition(positionEntity);

        return employeeRepository.save(entity);
    }


    @Override
    public void deleteById(Long id) {
        log.debug(String.format("Deleting Employee by id %s.%n", id));
        EmployeeEntity entity = employeeRepository.findById(id)
                .orElseThrow(() -> new DryCleaningApiException(
                        NO_SUCH_ENTITY + EmployeeEntity.class + " " + id));

        entity.setActive(false);
        employeeRepository.save(entity);
//        employeeRepository.deleteById(id);
    }

    @Override
    public EmployeeEntity getById(Long id) {
        log.debug(String.format("Getting employee by id: %s.%n", id));
        Optional<EmployeeEntity> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            return employee.get();
        } else {
            throw new DryCleaningApiException(THERE_IS_NO_SUCH_EMPLOYEE);
        }
    }

    @Override
    public List<EmployeeEntity> getAll() {
        log.debug(String.format("Getting all Employees.%n"));
        return employeeRepository.findAll();
    }

    @Override
    public EmployeeEntity update(EmployeeInDTO dto) {
        if (dto == null) {
            throw new DryCleaningApiException(DTO_MUST_NOT_BE_NULL_MESSAGE + dto);
        }

        log.debug(String.format("Updating Employee: %s", dto));

        employeeRepository.findById(dto.getId())
                .orElseThrow(() -> new DryCleaningApiException(
                        NO_SUCH_ENTITY + EmployeeEntity.class + " " + dto.getId()));

        PositionEntity positionEntity = positionRepository.findById(dto.getPositionId())
                .orElseThrow(() -> new DryCleaningApiException(
                        NO_SUCH_ENTITY + PositionEntity.class + " " + dto.getPositionId()));

        EmployeeEntity entity = mapper.convertValue(dto, EmployeeEntity.class);
        entity.setPosition(positionEntity);

        return employeeRepository.save(entity);
    }

    @Override
    public EmployeeInDTO toInDTO(EmployeeEntity employeeEntity) {
        return Optional.ofNullable(employeeEntity)
                .map(ent -> mapper.convertValue(ent, EmployeeInDTO.class))
                .orElseThrow(() -> new DryCleaningApiException(DTO_MUST_NOT_BE_NULL_MESSAGE));

    }

    @Override
    public EmployeeOutDTO toOutDTO(EmployeeEntity employeeEntity) {
        return Optional.ofNullable(employeeEntity)
                .map(ent -> mapper.convertValue(ent, EmployeeOutDTO.class))
                .orElseThrow(() -> new DryCleaningApiException(DTO_MUST_NOT_BE_NULL_MESSAGE));
    }

    public EmployeeEntity inDTOToEntity(EmployeeInDTO dto) {

        return Optional.ofNullable(dto)
                .map(ent -> mapper.convertValue(ent, EmployeeEntity.class))
                .orElseThrow(() -> new DryCleaningApiException(DTO_MUST_NOT_BE_NULL_MESSAGE));

    }


}

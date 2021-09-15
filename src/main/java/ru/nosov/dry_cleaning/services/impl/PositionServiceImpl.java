package ru.nosov.dry_cleaning.services.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nosov.dry_cleaning.dto.in.ClientInDTO;
import ru.nosov.dry_cleaning.dto.in.PositionInDTO;
import ru.nosov.dry_cleaning.dto.out.PositionOutDTO;
import ru.nosov.dry_cleaning.entities.ClientEntity;
import ru.nosov.dry_cleaning.entities.PositionEntity;
import ru.nosov.dry_cleaning.exceptions.DryCleaningApiException;
import ru.nosov.dry_cleaning.repositories.PositionRepository;
import ru.nosov.dry_cleaning.services.PositionService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
@RequiredArgsConstructor
public class PositionServiceImpl implements PositionService {

    private final PositionRepository positionRepository;
    private final ObjectMapper mapper;

    private static final String THERE_IS_NO_SUCH_POSITION = "There is no such Position!";
    private static final String DTO_MUST_NOT_BE_NULL_MESSAGE = "DTO must not be null!";

    @Transactional
    public PositionEntity create(PositionInDTO dto) {

        return positionRepository.save(inDTOToEntity(dto));
    }


    @Override
    public void deleteById(Long id) {
        log.debug(String.format("Deleting Position by id %s.%n", id));
        if (!positionRepository.existsById(id)) {
            throw new DryCleaningApiException(THERE_IS_NO_SUCH_POSITION);
        }
        positionRepository.deleteById(id);
    }

    @Override
    public PositionEntity getById(Long id) {
        log.debug(String.format("Getting position by id: %s.%n", id));
        Optional<PositionEntity> position = positionRepository.findById(id);
        if (position.isPresent()) {
            return position.get();
        } else {
            throw new DryCleaningApiException(THERE_IS_NO_SUCH_POSITION);
        }
    }

    @Override
    public List<PositionEntity> getAll() {
        log.debug(String.format("Getting all Positions.%n"));
        return positionRepository.findAll();
    }

    @Override
    public PositionEntity update(PositionInDTO dto) {
        log.debug(String.format("Updating Position: %s, ", dto.toString()));

        return positionRepository.save(inDTOToEntity(dto));
    }

    @Override
    public PositionInDTO toInDTO(PositionEntity positionEntity) {
        return Optional.ofNullable(positionEntity)
                .map(ent -> mapper.convertValue(ent, PositionInDTO.class))
                .orElseThrow(() -> new DryCleaningApiException(DTO_MUST_NOT_BE_NULL_MESSAGE));

    }

    @Override
    public PositionOutDTO toOutDTO(PositionEntity positionEntity) {
        return Optional.ofNullable(positionEntity)
                .map(ent -> mapper.convertValue(ent, PositionOutDTO.class))
                .orElseThrow(() -> new DryCleaningApiException(DTO_MUST_NOT_BE_NULL_MESSAGE));
    }

    public PositionEntity inDTOToEntity(PositionInDTO dto) {

        return Optional.ofNullable(dto)
                .map(ent -> mapper.convertValue(ent, PositionEntity.class))
                .orElseThrow(() -> new DryCleaningApiException(DTO_MUST_NOT_BE_NULL_MESSAGE));

    }


}

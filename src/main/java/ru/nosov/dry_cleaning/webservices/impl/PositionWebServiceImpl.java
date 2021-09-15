package ru.nosov.dry_cleaning.webservices.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nosov.dry_cleaning.dto.in.PositionInDTO;
import ru.nosov.dry_cleaning.dto.out.PositionOutDTO;
import ru.nosov.dry_cleaning.services.PositionService;
import ru.nosov.dry_cleaning.webservices.PositionWebService;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PositionWebServiceImpl implements PositionWebService {

    private final PositionService service;

    @Override
    public PositionOutDTO getById(Long id) {
        return service.toOutDTO(service.getById(id));
    }

    @Override
    public List<PositionOutDTO> getAll() {
        return service.getAll().stream()
                .map(service::toOutDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PositionOutDTO create(PositionInDTO dto) {
        return service.toOutDTO(service.create(dto));
    }

    @Override
    public PositionOutDTO update(PositionInDTO dto) {
        return service.toOutDTO(service.update(dto));
    }

    @Override
    public void deleteById(Long id) {
        service.deleteById(id);
    }
}

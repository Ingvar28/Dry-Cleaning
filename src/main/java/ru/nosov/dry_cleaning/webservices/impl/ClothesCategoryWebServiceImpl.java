package ru.nosov.dry_cleaning.webservices.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nosov.dry_cleaning.dto.in.ClothesCategoryInDTO;
import ru.nosov.dry_cleaning.dto.out.ClothesCategoryOutDTO;
import ru.nosov.dry_cleaning.services.ClothesCategoryService;
import ru.nosov.dry_cleaning.webservices.ClothesCategoryWebService;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClothesCategoryWebServiceImpl implements ClothesCategoryWebService {

    private final ClothesCategoryService service;

    @Override
    public ClothesCategoryOutDTO getById(Long id) {
        return service.toOutDTO(service.getById(id));
    }

    @Override
    public List<ClothesCategoryOutDTO> getAll() {
        return service.getAll().stream()
                .map(service::toOutDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ClothesCategoryOutDTO create(ClothesCategoryInDTO dto) {
        return service.toOutDTO(service.create(
                dto.getType(),
                dto.getSize(),
                dto.getPrice()
        ));
    }

    @Override
    public ClothesCategoryOutDTO update(ClothesCategoryInDTO dto) {
        return service.toOutDTO(service.update(
                dto.getId(),
                dto.getType(),
                dto.getSize(),
                dto.getPrice()
        ));
    }

    @Override
    public void delete(Long id) {
        service.deleteById(id);
    }
}

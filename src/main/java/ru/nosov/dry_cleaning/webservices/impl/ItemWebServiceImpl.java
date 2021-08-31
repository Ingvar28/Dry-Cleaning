package ru.nosov.dry_cleaning.webservices.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nosov.dry_cleaning.dto.in.ItemInDTO;
import ru.nosov.dry_cleaning.dto.out.ItemOutDTO;
import ru.nosov.dry_cleaning.services.ItemService;
import ru.nosov.dry_cleaning.webservices.ItemWebService;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemWebServiceImpl implements ItemWebService {

    private final ItemService service;

    @Override
    public ItemOutDTO getById(Long id) {
        return service.toOutDTO(service.getById(id));
    }

    @Override
    public List<ItemOutDTO> getAll() {
        return service.getAll().stream()
                .map(service::toOutDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ItemOutDTO create(ItemInDTO dto) {
        return service.toOutDTO(service.create(
                dto.getOrderId(),
                dto.getClothesCategoryId(),
                dto.getMaterial(),
                dto.getWash(),
                dto.getSqueezeOut(),
                dto.getDry(),
                dto.getIron(),
                dto.getWhite(),
                dto.getDryCleaning()
        ));
    }

    @Override
    public ItemOutDTO update(ItemInDTO dto) {
        return service.toOutDTO(service.update(
                dto.getId(),
                dto.getOrderId(),
                dto.getClothesCategoryId(),
                dto.getMaterial(),
                dto.getWash(),
                dto.getSqueezeOut(),
                dto.getDry(),
                dto.getIron(),
                dto.getWhite(),
                dto.getDryCleaning()
        ));
    }

    @Override
    public void delete(Long id) {
        service.deleteById(id);
    }
}

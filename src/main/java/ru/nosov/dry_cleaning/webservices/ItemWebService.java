package ru.nosov.dry_cleaning.webservices;

import ru.nosov.dry_cleaning.dto.in.ItemInDTO;
import ru.nosov.dry_cleaning.dto.out.ItemOutDTO;

import javax.transaction.Transactional;
import java.util.List;

public interface ItemWebService {

    ItemOutDTO getById(Long id);

    List<ItemOutDTO> getAll();

    @Transactional
    ItemOutDTO create(ItemInDTO dto);

    @Transactional
    ItemOutDTO update(ItemInDTO dto);

    @Transactional
    void deleteById(Long id);
}

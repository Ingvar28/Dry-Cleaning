package ru.nosov.dry_cleaning.webservices;

import ru.nosov.dry_cleaning.dto.in.ClothesCategoryInDTO;
import ru.nosov.dry_cleaning.dto.out.ClothesCategoryOutDTO;

import javax.transaction.Transactional;
import java.util.List;

public interface ClothesCategoryWebService {

    ClothesCategoryOutDTO getById(Long id);

    List<ClothesCategoryOutDTO> getAll();

    @Transactional
    ClothesCategoryOutDTO create(ClothesCategoryInDTO dto);

    @Transactional
    ClothesCategoryOutDTO update(ClothesCategoryInDTO dto);

    @Transactional
    void delete(Long id);
}

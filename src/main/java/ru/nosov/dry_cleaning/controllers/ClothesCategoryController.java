package ru.nosov.dry_cleaning.controllers;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.nosov.dry_cleaning.dto.in.ClothesCategoryInDTO;
import ru.nosov.dry_cleaning.dto.out.ClothesCategoryOutDTO;
import ru.nosov.dry_cleaning.webservices.ClothesCategoryWebService;

import java.util.List;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/clothescategory")
public class ClothesCategoryController {

    private final ClothesCategoryWebService service;

    @GetMapping("/all")
    public List<ClothesCategoryOutDTO> getAll() {
        return service.getAll();
    }

    @GetMapping(value = {"", "/{id}"})
    public ClothesCategoryOutDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public ClothesCategoryOutDTO create(@RequestBody ClothesCategoryInDTO dto) {
        return service.create(dto);
    }

    @PutMapping
    public ClothesCategoryOutDTO update(@RequestBody ClothesCategoryInDTO dto) {
        return service.update(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}

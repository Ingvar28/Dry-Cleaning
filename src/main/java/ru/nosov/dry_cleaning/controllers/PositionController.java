package ru.nosov.dry_cleaning.controllers;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.nosov.dry_cleaning.dto.in.PositionInDTO;
import ru.nosov.dry_cleaning.dto.out.PositionOutDTO;
import ru.nosov.dry_cleaning.webservices.PositionWebService;

import java.util.List;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/position")
public class PositionController {

    private final PositionWebService service;

    @GetMapping("/all")
    public List<PositionOutDTO> getAll() {
        return service.getAll();
    }

    @GetMapping(value = {"", "{/id}"})
    public PositionOutDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public PositionOutDTO create(@RequestBody PositionInDTO dto) {
        return service.create(dto);
    }

    @PutMapping
    public PositionOutDTO update(@RequestBody PositionInDTO dto) {
        return service.update(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}

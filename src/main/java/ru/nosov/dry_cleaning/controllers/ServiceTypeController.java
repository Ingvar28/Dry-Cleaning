package ru.nosov.dry_cleaning.controllers;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.nosov.dry_cleaning.dto.in.ServiceTypeInDTO;
import ru.nosov.dry_cleaning.dto.out.ServiceTypeOutDTO;
import ru.nosov.dry_cleaning.webservices.ServiceTypeWebService;

import java.util.List;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/servicetype")
public class ServiceTypeController {

    private final ServiceTypeWebService service;

    @GetMapping("/all")
    public List<ServiceTypeOutDTO> getAll() {
        return service.getAll();
    }

    @GetMapping(value = {"", "/{id}"})
    public ServiceTypeOutDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public ServiceTypeOutDTO create(@RequestBody ServiceTypeInDTO dto) {
        return service.create(dto);
    }

    @PutMapping
    public ServiceTypeOutDTO update(@RequestBody ServiceTypeInDTO dto) {
        return service.update(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}

package ru.nosov.dry_cleaning.controllers;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.nosov.dry_cleaning.dto.in.EmployeeInDTO;
import ru.nosov.dry_cleaning.dto.out.EmployeeOutDTO;
import ru.nosov.dry_cleaning.webservices.EmployeeWebService;

import java.util.List;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/employee")
public class EmployeeController {

    private final EmployeeWebService service;

    @GetMapping("/all")
    public List<EmployeeOutDTO> getAll() {
        return service.getAll();
    }

    @GetMapping(value = {"", "/{id}"})
    public EmployeeOutDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public EmployeeOutDTO create(@RequestBody EmployeeInDTO dto) {
        return service.create(dto);
    }

    @PutMapping
    public EmployeeOutDTO update(@RequestBody EmployeeInDTO dto) {
        return service.update(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteById(id);
    }

}

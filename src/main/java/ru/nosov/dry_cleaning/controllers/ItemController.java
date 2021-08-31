package ru.nosov.dry_cleaning.controllers;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.nosov.dry_cleaning.dto.in.ItemInDTO;
import ru.nosov.dry_cleaning.dto.out.ItemOutDTO;
import ru.nosov.dry_cleaning.webservices.ItemWebService;

import java.util.List;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/item", method = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
public class ItemController {

    private final ItemWebService service;

    @GetMapping("/all")
    public List<ItemOutDTO> getAll() {
        return service.getAll();
    }

    @GetMapping(value = {"", "{/id}"})
    public ItemOutDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public ItemOutDTO create(@RequestBody ItemInDTO dto) {
        return service.create(dto);
    }

    @PutMapping
    public ItemOutDTO update(@RequestBody ItemInDTO dto) {
        return service.update(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}

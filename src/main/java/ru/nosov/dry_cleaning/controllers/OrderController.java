package ru.nosov.dry_cleaning.controllers;



import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.nosov.dry_cleaning.dto.in.OrderInDTO;
import ru.nosov.dry_cleaning.dto.out.OrderOutDTO;
import ru.nosov.dry_cleaning.webservices.OrderWebService;

import java.util.List;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/order")
public class OrderController {

    private final OrderWebService service;

    @GetMapping("/all")
    public List<OrderOutDTO> getAll() {
        return service.getAll();
    }

    @GetMapping(value = {"", "/{id}"})
    public OrderOutDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public OrderOutDTO create(@RequestBody OrderInDTO dto) {
        return service.create(dto);
    }

    @PutMapping
    public OrderOutDTO update(@RequestBody OrderInDTO dto) {
        return service.update(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}

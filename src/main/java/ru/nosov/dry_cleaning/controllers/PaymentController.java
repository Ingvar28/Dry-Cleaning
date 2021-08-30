package ru.nosov.dry_cleaning.controllers;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.nosov.dry_cleaning.dto.in.PaymentInDTO;
import ru.nosov.dry_cleaning.dto.out.PaymentOutDTO;
import ru.nosov.dry_cleaning.webservices.PaymentWebService;

import java.util.List;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/payment", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class PaymentController {

    private final PaymentWebService service;

    @GetMapping("/all")
    public List<PaymentOutDTO> getAll() {
        return service.getAll();
    }

    @GetMapping(value = {"", "{/id}"})
    public PaymentOutDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public PaymentOutDTO create(@RequestBody PaymentInDTO dto) {
        return service.create(dto);
    }

    @PutMapping
    public PaymentOutDTO update(@RequestBody PaymentInDTO dto) {
        return service.update(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}

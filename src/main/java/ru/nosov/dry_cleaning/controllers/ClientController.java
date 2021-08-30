package ru.nosov.dry_cleaning.controllers;



import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.nosov.dry_cleaning.dto.in.ClientInDTO;
import ru.nosov.dry_cleaning.dto.out.ClientOutDTO;
import ru.nosov.dry_cleaning.webservices.ClientWebService;

import java.util.List;


@Slf4j
@RestController
@RequiredArgsConstructor
@Controller
@RequestMapping(value = "/client", method = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
public class ClientController {

    private final ClientWebService service;

    @GetMapping("/all")
    public List<ClientOutDTO> getAll() {
        return service.getAll();
    }

    @GetMapping(value = {"", "{/id}"})
    public ClientOutDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public ClientOutDTO create(@RequestBody ClientInDTO dto) {
        return service.create(dto);
    }

    @PutMapping
    public ClientOutDTO update(@RequestBody ClientInDTO dto) {
        return service.update(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}

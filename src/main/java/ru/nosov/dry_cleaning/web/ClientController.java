package ru.nosov.dry_cleaning.web;

import ru.nosov.dry_cleaning.dto.ClientDTO;
import ru.nosov.dry_cleaning.services.ClientService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/client")
public class ClientController {



    @GetMapping//(value = {"", "/{id}"})
    public String getSomeData(){
        return "Hello Spring";
    }

    @GetMapping(value = {"", "/{id}"})
    public List<ClientDTO> getById(@PathVariable Optional<Long> id) {
        return service.getById(id);
    }

    @PostMapping
    public ClientDTO create(@RequestBody ClientDTO dto) {
        return service.create(dto);
    }

    @PutMapping
    public ClientDTO update(@RequestBody ClientDTO dto) {
        return service.update(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}

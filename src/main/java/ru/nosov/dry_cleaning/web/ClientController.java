package ru.nosov.dry_cleaning.web;



import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;




@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/client")
public class ClientController {

    //Дописать Веб сервис
//    @GetMapping(value = {"", "/{id}"})
//    public List<ClientDTO> getById(@PathVariable Optional<Long> id) {
//        return service.getById(id);
//    }
//
//    @PostMapping
//    public ClientDTO create(@RequestBody ClientDTO dto) {
//        return service.create(dto);
//    }
//
//    @PutMapping
//    public ClientDTO update(@RequestBody ClientDTO dto) {
//        return service.update(dto);
//    }
//
//    @DeleteMapping("/{id}")
//    public void delete(@PathVariable Long id) {
//        service.delete(id);
//    }

}

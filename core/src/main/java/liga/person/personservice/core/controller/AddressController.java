package liga.person.personservice.core.controller;

import liga.person.personservice.core.mapper.AddressMapper;
import liga.person.personservice.core.model.Address;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/address")
public class AddressController {

    private AddressMapper mapper;

    @GetMapping
    public List<Address> findAll() {
        log.info("Calling the findAll method on an entity Address");
        return mapper.findAll();
    }

    @GetMapping("/{id}")
    public Address findById(@PathVariable(name = "id") Long id) {
        Address address = mapper.findByID(id);
        log.info("Call object " + address + " of entity Address via method findById");
        return address;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody Address address) {
        mapper.save(address);
        log.info("Object " + address + " of entity Address is created in method save");
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable(name = "id") Long id) {
        mapper.deleteById(id);
        log.info("Deleted entity object Address by id " + id);
    }
}

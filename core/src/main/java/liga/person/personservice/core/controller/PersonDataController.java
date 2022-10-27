package liga.person.personservice.core.controller;

import liga.person.personservice.core.mapper.PersonDataMapper;
import liga.person.personservice.core.model.PersonData;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/personData")
public class PersonDataController {

    private PersonDataMapper mapper;

    @GetMapping
    public List<PersonData> findAll() {
        log.info("Calling the findAll method on an entity PersonData");
        return mapper.findAll();
    }

    @GetMapping("/{id}")
    public PersonData findById(@PathVariable(name = "id") Long id) {
        PersonData personData = mapper.findByID(id);
        log.info("Call object " + personData + " of entity PersonData via method findById");
        return personData;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody PersonData personData) {
        mapper.save(personData);
        log.info("Object " + personData + " of entity PersonData is created in method save");
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable(name = "id") Long id) {
        mapper.deleteById(id);
        log.info("Deleted entity object PersonData by id " + id);
    }
}

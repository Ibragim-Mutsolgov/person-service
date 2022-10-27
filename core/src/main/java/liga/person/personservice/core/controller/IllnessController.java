package liga.person.personservice.core.controller;

import liga.person.personservice.core.mapper.IllnessMapper;
import liga.person.personservice.core.model.Illness;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/illness")
public class IllnessController {

    private IllnessMapper mapper;

    @GetMapping
    public List<Illness> findAll() {
        log.info("Calling the findAll method on an entity Illness");
        return mapper.findAll();
    }

    @GetMapping("/{id}")
    public Illness findById(@PathVariable(name = "id") Long id) {
        Illness illness = mapper.findByID(id);
        log.info("Call object " + illness + " of entity Illness via method findById");
        return illness;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody Illness illness) {
        mapper.save(illness);
        log.info("Object " + illness + " of entity Illness is created in method save");
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable(name = "id") Long id) {
        mapper.deleteById(id);
        log.info("Deleted entity object Illness by id " + id);
    }
}

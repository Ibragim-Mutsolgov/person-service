package liga.person.personservice.core.controller;

import liga.person.personservice.core.mapper.MedicalCardMapper;
import liga.person.personservice.core.model.MedicalCard;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/medicalCard")
public class MedicalCardController {

    private MedicalCardMapper mapper;

    @GetMapping
    public List<MedicalCard> findAll() {
        log.info("Calling the findAll method on an entity MedicalCard");
        return mapper.findAll();
    }

    @GetMapping("/{id}")
    public MedicalCard findById(@PathVariable(name = "id") Long id) {
        MedicalCard medicalCard = mapper.findByID(id);
        log.info("Call object " + medicalCard + " of entity MedicalCard via method findById");
        return medicalCard;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody MedicalCard medicalCard) {
        mapper.save(medicalCard);
        log.info("Object " + medicalCard + " of entity MedicalCard is created in method save");
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable(name = "id") Long id) {
        mapper.deleteById(id);
        log.info("Deleted entity object MedicalCard by id " + id);
    }
}

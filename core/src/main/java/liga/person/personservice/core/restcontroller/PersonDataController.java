package liga.person.personservice.core.restcontroller;

import liga.person.personservice.core.model.PersonData;
import liga.person.personservice.core.service.PersonDataService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/personData")
public class PersonDataController {

    private PersonDataService service;

    @GetMapping
    public ResponseEntity<List<PersonData>> findAll() {
        try {
            return ResponseEntity.ok().body(service.findAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonData> findById(@PathVariable(name = "id") Long id) {
        try {
            PersonData personData = service.findById(id);
            if (personData != null) return ResponseEntity.ok(personData);
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<PersonData> save(@RequestBody PersonData personData) {
        try {
            PersonData personDataSave = service.save(personData);
            return ResponseEntity.status(HttpStatus.CREATED).body(personDataSave);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonData> savePut(@PathVariable(name = "id") Long id, @RequestBody PersonData personData) {
        try {
            PersonData personDataSave = service.savePut(id, personData);
            return ResponseEntity.ok().body(personDataSave);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable(name = "id") Long id) {
        try {
            service.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}

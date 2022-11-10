package liga.person.personservice.core.restcontroller;

import liga.person.personservice.core.model.Illness;
import liga.person.personservice.core.service.IllnessService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/illness")
public class IllnessController {

    private IllnessService service;

    public IllnessController(IllnessService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Illness>> findAll() {
        try {
            return ResponseEntity.ok().body(service.findAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Illness> findById(@PathVariable(name = "id") Long id) {
        try {
            Illness illness = service.findById(id);
            if (illness != null) return ResponseEntity.ok().body(illness);
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<Illness> save(@RequestBody Illness illness) {
        try {
            Illness illnessSave = service.save(illness);
            return ResponseEntity.status(HttpStatus.CREATED).body(illnessSave);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> savePut(@PathVariable(name = "id") Long id, @RequestBody Illness illness) {
        try {
            Illness illnessSave = service.savePut(id, illness);
            return ResponseEntity.ok().body(illnessSave);
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

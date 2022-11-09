package liga.person.personservice.core.restcontroller;

import liga.person.personservice.core.model.MedicalCard;
import liga.person.personservice.core.service.MedicalCardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/medicalCard")
public class MedicalCardController {

    private MedicalCardService service;

    public MedicalCardController(MedicalCardService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<MedicalCard>> findAll() {
        try {
            return ResponseEntity.ok().body(service.findAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicalCard> findById(@PathVariable(name = "id") Long id) {
        try {
            MedicalCard medicalCard = service.findById(id);
            if (medicalCard != null) return ResponseEntity.ok(medicalCard);
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<MedicalCard> save(@RequestBody MedicalCard medicalCard) {
        try {
            MedicalCard medicalCardSave = service.save(medicalCard);
            return ResponseEntity.status(HttpStatus.CREATED).body(medicalCardSave);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicalCard> savePut(@PathVariable(name = "id") Long id, @RequestBody MedicalCard medicalCard) {
        try {
            MedicalCard medicalCardSave = service.savePut(id, medicalCard);
            return ResponseEntity.ok().body(medicalCardSave);
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

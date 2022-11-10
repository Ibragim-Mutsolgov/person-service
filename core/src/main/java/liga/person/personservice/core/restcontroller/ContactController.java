package liga.person.personservice.core.restcontroller;

import liga.person.personservice.core.model.Contact;
import liga.person.personservice.core.service.ContactService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contact")
public class ContactController {

    private ContactService service;

    public ContactController(ContactService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Contact>> findAll() {
        try {
            return ResponseEntity.ok().body(service.findAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contact> findById(@PathVariable(name = "id") Long id) {
        try {
            Contact contact = service.findById(id);
            if (contact != null) return ResponseEntity.ok(contact);
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<Contact> save(@RequestBody Contact contact) {
        try {
            Contact contactSave = service.save(contact);
            return ResponseEntity.status(HttpStatus.CREATED).body(contactSave);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contact> savePut(@PathVariable(name = "id") Long id, @RequestBody Contact contact) {
        try {
            Contact contactSave = service.savePut(id, contact);
            return ResponseEntity.ok().body(contactSave);
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

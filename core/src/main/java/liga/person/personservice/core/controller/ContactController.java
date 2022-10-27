package liga.person.personservice.core.controller;

import liga.person.personservice.core.mapper.ContactMapper;
import liga.person.personservice.core.model.Contact;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/contact")
public class ContactController {

    private ContactMapper mapper;

    @GetMapping
    public List<Contact> findAll() {
        log.info("Calling the findAll method on an entity Contact");
        return mapper.findAll();
    }

    @GetMapping("/{id}")
    public Contact findById(@PathVariable(name = "id") Long id) {
        Contact contact = mapper.findByID(id);
        log.info("Call object " + contact + " of entity Contact via method findById");
        return contact;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody Contact contact) {
        mapper.save(contact);
        log.info("Object " + contact + " of entity Contact is created in method save");
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable(name = "id") Long id) {
        mapper.deleteById(id);
        log.info("Deleted entity object Contact by id " + id);
    }
}

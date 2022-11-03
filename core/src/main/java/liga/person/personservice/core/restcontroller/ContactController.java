package liga.person.personservice.core.restcontroller;

import liga.person.personservice.core.dto.ContactDto;
import liga.person.personservice.core.mapper.ContactMapper;
import liga.person.personservice.core.service.SystemSettings;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/contact")
public class ContactController {

    private final ContactMapper mapper;

    private final Logger logger = Logger.getLogger(ContactController.class.getName());

    public ContactController(ContactMapper mapper) {
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<ContactDto>> findAll() {
        try {
            String username = SystemSettings.getUsername();
            logger.info(new Date() + ": Вызов метода findAll() в классе ContactController пользователем " + username);
            return ResponseEntity.ok().body(mapper.findAll());
        } catch (Exception e) {
            logger.info(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactDto> findById(@PathVariable(name = "id") Long id) {
        try {
            ContactDto contact = mapper.findByID(id);
            String username = SystemSettings.getUsername();
            logger.info(new Date() + ": Вызов метода findById(" + id + ") в классе ContactController пользователем " + username);
            if (contact != null) return ResponseEntity.ok(contact);
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            logger.info(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody ContactDto contact) {
        try {
            mapper.save(contact);
            String username = SystemSettings.getUsername();
            logger.info(new Date() + ": Вызов метода save(" + contact + ") в классе ContactController пользователем " + username);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            logger.info(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable(name = "id") Long id) {
        try {
            mapper.deleteById(id);
            String username = SystemSettings.getUsername();
            logger.info(new Date() + ": Вызов метода deleteById(" + id + ") в классе ContactController пользователем " + username);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            logger.info(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}

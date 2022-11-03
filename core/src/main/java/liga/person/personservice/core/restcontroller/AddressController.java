package liga.person.personservice.core.restcontroller;

import liga.person.personservice.core.dto.AddressDto;
import liga.person.personservice.core.mapper.AddressMapper;
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
@RequestMapping("/address")
public class AddressController {

    private final AddressMapper mapper;

    private final Logger logger = Logger.getLogger(AddressController.class.getName());

    public AddressController(AddressMapper mapper) {
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<AddressDto>> findAll() {
        try {
            String username = SystemSettings.getUsername();
            logger.info(new Date() + ": Вызов метода findAll() в классе AddressController пользователем " + username);
            return ResponseEntity.ok().body(mapper.findAll());
        } catch (Exception e) {
            logger.info(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressDto> findById(@PathVariable(name = "id") Long id) {
        try {
            AddressDto address = mapper.findByID(id);
            String username = SystemSettings.getUsername();
            logger.info(new Date() + ": Вызов метода findById(" + id + ") в классе AddressController пользователем " + username);
            if (address != null) return ResponseEntity.ok(address);
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            logger.info(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody AddressDto address) {
        try {
            mapper.save(address);
            String username = SystemSettings.getUsername();
            logger.info(new Date() + ": Вызов метода save(" + address + ") в классе AddressController пользователем " + username);
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
            logger.info(new Date() + ": Вызов метода deleteById(" + id + ") в классе AddressController пользователем " + username);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            logger.info(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}

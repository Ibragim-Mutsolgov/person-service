package liga.person.personservice.core.restcontroller;

import liga.person.personservice.core.dto.IllnessDto;
import liga.person.personservice.core.mapper.IllnessMapper;
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
@RequestMapping("/illness")
public class IllnessController {

    private final IllnessMapper mapper;

    private final Logger logger = Logger.getLogger(IllnessController.class.getName());

    public IllnessController(IllnessMapper mapper) {
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<IllnessDto>> findAll() {
        try {
            String username = SystemSettings.getUsername();
            logger.info(new Date() + ": Вызов метода findAll() в классе IllnessController пользователем " + username);
            return ResponseEntity.ok().body(mapper.findAll());
        } catch (Exception e) {
            logger.info(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<IllnessDto> findById(@PathVariable(name = "id") Long id) {
        try {
            IllnessDto illness = mapper.findByID(id);
            String username = SystemSettings.getUsername();
            logger.info(new Date() + ": Вызов метода findById(" + id + ") в классе IllnessController пользователем " + username);
            if (illness != null) return ResponseEntity.ok().body(illness);
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            logger.info(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody IllnessDto illness) {
        try {
            mapper.save(illness);
            String username = SystemSettings.getUsername();
            logger.info(new Date() + ": Вызов метода save(" + illness + ") в классе IllnessController пользователем " + username);
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
            logger.info(new Date() + ": Вызов метода deleteById(" + id + ") в классе IllnessController пользователем " + username);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            logger.info(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}

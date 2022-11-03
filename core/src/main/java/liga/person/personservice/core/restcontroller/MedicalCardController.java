package liga.person.personservice.core.restcontroller;

import liga.person.personservice.core.dto.MedicalCardDto;
import liga.person.personservice.core.mapper.MedicalCardMapper;
import liga.person.personservice.core.service.SystemSettings;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/medicalCard")
public class MedicalCardController {

    private final MedicalCardMapper mapper;

    private final Logger logger = Logger.getLogger(MedicalCardController.class.getName());

    public MedicalCardController(MedicalCardMapper mapper) {
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<MedicalCardDto>> findAll() {
        try {
            String username = SystemSettings.getUsername();
            logger.info(new Date() + ": Вызов метода findAll() в классе MedicalCardController пользователем " + username);
            return ResponseEntity.ok().body(mapper.findAll());
        } catch (Exception e) {
            logger.info(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicalCardDto> findById(@PathVariable(name = "id") Long id) {
        try {
            MedicalCardDto medicalCard = mapper.findByID(id);
            String username = SystemSettings.getUsername();
            logger.info(new Date() + ": Вызов метода findById(" + id + ") в классе MedicalCardController пользователем " + username);
            if (medicalCard != null) return ResponseEntity.ok(medicalCard);
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            logger.info(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody MedicalCardDto medicalCard) {
        try {
            mapper.save(medicalCard);
            String username = SystemSettings.getUsername();
            logger.info(new Date() + ": Вызов метода save(" + medicalCard + ") в классе MedicalCardController пользователем " + username);
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
            logger.info(new Date() + ": Вызов метода deleteById(" + id + ") в классе MedicalCardController пользователем " + username);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            logger.info(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}

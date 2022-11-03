package liga.person.personservice.core.restcontroller;

import liga.person.personservice.core.dto.PersonDataDto;
import liga.person.personservice.core.mapper.PersonDataMapper;
import liga.person.personservice.core.repository.LogsRepository;
import liga.person.personservice.core.service.SystemSettings;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/personData")
public class PersonDataController {

    private PersonDataMapper mapper;

    private LogsRepository repository;

    @GetMapping
    public ResponseEntity<List<PersonDataDto>> findAll() {
        try {
            SystemSettings.saveToDbAndFile(repository, "Класс PersonDataController метод findAll(). Вызов списка всех пользователей", SystemSettings.getUsername());
            return ResponseEntity.ok().body(mapper.findAll());
        } catch (Exception e) {
            SystemSettings.saveToDbAndFile(repository, e.getMessage(), SystemSettings.getUsername());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDataDto> findById(@PathVariable(name = "id") Long id) {
        try {
            PersonDataDto personData = mapper.findByID(id);
            SystemSettings.saveToDbAndFile(repository, "Класс PersonDataController метод findById(" + id + "). Поиск по id", SystemSettings.getUsername());
            if (personData != null) return ResponseEntity.ok(personData);
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            SystemSettings.saveToDbAndFile(repository, e.getMessage(), SystemSettings.getUsername());
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody PersonDataDto personData) {
        try {
            mapper.save(personData);
            SystemSettings.saveToDbAndFile(repository, "Класс PersonDataController метод save(" + personData + "). Сохранение данных", SystemSettings.getUsername());
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            SystemSettings.saveToDbAndFile(repository, e.getMessage(), SystemSettings.getUsername());
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable(name = "id") Long id) {
        try {
            mapper.deleteById(id);
            SystemSettings.saveToDbAndFile(repository, "Класс PersonDataController метод deleteById(" + id + "). Удаление данных", SystemSettings.getUsername());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            SystemSettings.saveToDbAndFile(repository, e.getMessage(), SystemSettings.getUsername());
            return ResponseEntity.badRequest().build();
        }
    }
}

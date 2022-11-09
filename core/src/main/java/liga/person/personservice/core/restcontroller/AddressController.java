package liga.person.personservice.core.restcontroller;

import liga.person.personservice.core.model.Address;
import liga.person.personservice.core.service.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/address")
public class AddressController {

    private AddressService service;

    public AddressController(AddressService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Address>> findAll() {
        try {
            return ResponseEntity.ok().body(service.findAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> findById(@PathVariable(name = "id") Long id) {
        try {
            Address address = service.findById(id);
            if (address != null) return ResponseEntity.ok(address);
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<Address> save(@RequestBody Address address) {
        try {
            Address addressSave = service.save(address);
            return ResponseEntity.status(HttpStatus.CREATED).body(addressSave);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Address> savePut(@PathVariable(name = "id") Long id, @RequestBody Address address) {
        try {
            Address addressSave = service.savePut(id, address);
            return ResponseEntity.ok().body(addressSave);
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

package liga.person.personservice.core.service;

import liga.person.personservice.core.model.Contact;

import java.util.List;

public interface ContactService {

    List<Contact> findAll();

    Contact findById(Long id);

    Contact save(Contact contact);

    Contact savePut(Long id, Contact contact);

    void deleteById(Long id);
}

package liga.person.personservice.core.service.serviceimpl;

import liga.person.personservice.core.model.Contact;
import liga.person.personservice.core.repository.ContactRepository;
import liga.person.personservice.core.service.ContactService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

    private ContactRepository repository;

    public ContactServiceImpl(ContactRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Contact> findAll() {
        return repository.findAll();
    }

    @Override
    public Contact findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Contact save(Contact contact) {
        return repository.save(contact);
    }

    @Override
    public Contact savePut(Long id, Contact contact) {
        return repository.findById(id)
                .map(contactSave -> {
                    contactSave.setId(id);
                    contactSave.setPhoneNumber(contact.getPhoneNumber());
                    contactSave.setEmail(contact.getEmail());
                    contactSave.setProfileLink(contact.getProfileLink());
                    return repository.save(contactSave);
                }).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}

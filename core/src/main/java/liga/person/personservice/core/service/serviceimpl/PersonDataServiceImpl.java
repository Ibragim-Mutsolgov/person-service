package liga.person.personservice.core.service.serviceimpl;

import liga.person.personservice.core.model.PersonData;
import liga.person.personservice.core.repository.PersonDataRepository;
import liga.person.personservice.core.service.PersonDataService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonDataServiceImpl implements PersonDataService {

    private PersonDataRepository repository;

    public PersonDataServiceImpl(PersonDataRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<PersonData> findAll() {
        return repository.findAll();
    }

    @Override
    public PersonData findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public PersonData save(PersonData personData) {
        return repository.save(personData);
    }

    @Override
    public PersonData savePut(Long id, PersonData personData) {
        return repository.findById(id)
                .map(personDataSave -> {
                    personDataSave.setId(id);
                    personDataSave.setLastName(personData.getLastName());
                    personDataSave.setFirstName(personData.getFirstName());
                    personDataSave.setBirthDt(personData.getBirthDt());
                    personDataSave.setAge(personData.getAge());
                    personDataSave.setSex(personData.getSex());
                    personDataSave.setMedicalCard(personData.getMedicalCard());
                    personDataSave.setParent(personData.getParent());
                    return repository.save(personDataSave);
                })
                .orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}

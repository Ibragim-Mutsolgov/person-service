package liga.person.personservice.core.service;

import liga.person.personservice.core.model.PersonData;

import java.util.List;

public interface PersonDataService {

    List<PersonData> findAll();

    PersonData findById(Long id);

    PersonData save(PersonData personData);

    PersonData savePut(Long id, PersonData personData);

    void deleteById(Long id);
}

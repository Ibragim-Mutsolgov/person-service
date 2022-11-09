package liga.person.personservice.core.service;

import liga.person.personservice.core.model.MedicalCard;

import java.util.List;

public interface MedicalCardService {

    List<MedicalCard> findAll();

    MedicalCard findById(Long id);

    MedicalCard save(MedicalCard medicalCard);

    MedicalCard savePut(Long id, MedicalCard medicalCard);

    void deleteById(Long id);
}

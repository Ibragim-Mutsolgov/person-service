package liga.person.personservice.core.service.serviceimpl;

import liga.person.personservice.core.model.MedicalCard;
import liga.person.personservice.core.repository.MedicalCardRepository;
import liga.person.personservice.core.service.MedicalCardService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalCardServiceImpl implements MedicalCardService {

    private MedicalCardRepository repository;

    public MedicalCardServiceImpl(MedicalCardRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<MedicalCard> findAll() {
        return repository.findAll();
    }

    @Override
    public MedicalCard findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public MedicalCard save(MedicalCard medicalCard) {
        return repository.save(medicalCard);
    }

    @Override
    public MedicalCard savePut(Long id, MedicalCard medicalCard) {
        return repository.findById(id)
                .map(medicalCardSave -> {
                    medicalCardSave.setId(id);
                    medicalCardSave.setClientStatus(medicalCard.getClientStatus());
                    medicalCardSave.setMedStatus(medicalCard.getMedStatus());
                    medicalCardSave.setRegistryDt(medicalCard.getRegistryDt());
                    medicalCardSave.setComment(medicalCard.getComment());
                    return repository.save(medicalCardSave);
                })
                .orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}

package liga.person.personservice.core.service.serviceimpl;

import liga.person.personservice.core.model.Illness;
import liga.person.personservice.core.repository.IllnessRepository;
import liga.person.personservice.core.service.IllnessService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class IllnessServiceImpl implements IllnessService {

    private IllnessRepository repository;

    public IllnessServiceImpl(IllnessRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Illness> findAll() {
        return repository.findAll();
    }

    @Override
    public Illness findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Illness save(Illness illness) {
        return repository.save(illness);
    }

    @Override
    public Illness savePut(Long id, Illness illness) {
        return repository.findById(id)
                .map(illnessSave -> {
                    illnessSave.setId(id);
                    illnessSave.setMedicalCard(illness.getMedicalCard());
                    illnessSave.setTypeId(illness.getTypeId());
                    illnessSave.setHeaviness(illness.getHeaviness());
                    illnessSave.setAppearanceDttm(illness.getAppearanceDttm());
                    illnessSave.setRecoveryDt(illness.getRecoveryDt());
                    return repository.save(illnessSave);
                })
                .orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}

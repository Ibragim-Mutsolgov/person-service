package liga.person.personservice.core.service;

import liga.person.personservice.core.model.Illness;

import java.util.List;

public interface IllnessService {

    List<Illness> findAll();

    Illness findById(Long id);

    Illness save(Illness illness);

    Illness savePut(Long id, Illness illness);

    void deleteById(Long id);
}

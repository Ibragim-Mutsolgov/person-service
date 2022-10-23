package liga.person.personservice.core.repository;

import liga.person.personservice.core.model.MedicalCard;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalCardRepository extends CrudRepository<MedicalCard, Long> {
}

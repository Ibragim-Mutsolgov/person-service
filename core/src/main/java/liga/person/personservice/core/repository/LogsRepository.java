package liga.person.personservice.core.repository;

import liga.person.personservice.core.model.Logs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LogsRepository extends JpaRepository<Logs, UUID> {
}

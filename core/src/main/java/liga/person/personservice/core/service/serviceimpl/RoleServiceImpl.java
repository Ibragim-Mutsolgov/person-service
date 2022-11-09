package liga.person.personservice.core.service.serviceimpl;

import liga.person.personservice.core.model.Role;
import liga.person.personservice.core.repository.RoleRepository;
import liga.person.personservice.core.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository repository;

    public RoleServiceImpl(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Role save(Role role) {
        return repository.save(role);
    }
}

package liga.person.personservice.core.service.serviceimpl;

import liga.person.personservice.core.model.User;
import liga.person.personservice.core.repository.UserRepository;
import liga.person.personservice.core.service.UserService;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User findByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public User save(User user) {
        return repository.save(user);
    }
}

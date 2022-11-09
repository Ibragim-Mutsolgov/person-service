package liga.person.personservice.core.service;

import liga.person.personservice.core.model.User;

public interface UserService {

    User findByUsername(String username);

    User save(User user);
}

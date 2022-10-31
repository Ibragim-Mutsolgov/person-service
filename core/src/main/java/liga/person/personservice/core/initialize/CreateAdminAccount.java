package liga.person.personservice.core.initialize;

import liga.person.personservice.core.model.Role;
import liga.person.personservice.core.model.User;
import liga.person.personservice.core.repository.RoleRepository;
import liga.person.personservice.core.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;

@Component
@AllArgsConstructor
public class CreateAdminAccount implements CommandLineRunner {

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (userRepository.findByUsername("login") == null) {
            User user = new User();
            user.setUsername("login");
            user.setPassword(passwordEncoder.encode("pass"));
            Role role = new Role();
            role.setRole("ADMIN");
            role = roleRepository.save(role);
            Set<Role> roles = new LinkedHashSet<>();
            roles.add(role);
            user.setRoles(roles);
            userRepository.save(user);
        }
    }
}

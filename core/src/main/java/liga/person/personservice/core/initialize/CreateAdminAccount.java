package liga.person.personservice.core.initialize;

import liga.person.personservice.core.model.Role;
import liga.person.personservice.core.model.User;
import liga.person.personservice.core.service.RoleService;
import liga.person.personservice.core.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Logger;

@Component
@AllArgsConstructor
public class CreateAdminAccount implements CommandLineRunner {

    private UserService userService;

    private RoleService roleService;

    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (userService.findByUsername("login") == null) {
            User user = new User();
            user.setUsername("login");
            user.setPassword(passwordEncoder.encode("pass"));
            Role role = new Role();
            role.setRole("ADMIN");
            role = roleService.save(role);
            Set<Role> roles = new LinkedHashSet<>();
            roles.add(role);
            user.setRoles(roles);
            userService.save(user);
            Logger logger = Logger.getLogger(CreateAdminAccount.class.getName());
            logger.info("Класс: CreateAdminAccount; метод: run()");
        }
    }
}

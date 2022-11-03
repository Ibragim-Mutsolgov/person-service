package liga.person.personservice.core.controller;

import liga.person.personservice.core.dto.UsersDataForRegistration;
import liga.person.personservice.core.model.Role;
import liga.person.personservice.core.model.User;
import liga.person.personservice.core.repository.RoleRepository;
import liga.person.personservice.core.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Logger;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final Logger logger = Logger.getLogger(RegistrationController.class.getName());

    public RegistrationController(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String getPage() {
        return "registration";
    }

    @PostMapping
    public String getRegistration(UsersDataForRegistration usersDataForRegistration) {
        if (usersDataForRegistration.getUsername() != null &&
                !Objects.equals(usersDataForRegistration.getUsername(), "") &&
                usersDataForRegistration.getPassword() != null &&
                !Objects.equals(usersDataForRegistration.getPassword(), "")) {
            User user = new User();
            user.setUsername(usersDataForRegistration.getUsername());
            user.setPassword(passwordEncoder.encode(usersDataForRegistration.getPassword()));
            Role role = new Role();
            role.setRole("USER");
            role = roleRepository.save(role);
            Set<Role> roles = new LinkedHashSet<>();
            roles.add(role);
            user.setRoles(roles);
            userRepository.save(user);
            logger.info(new Date() + ": Осуществлена регистрация пользователя " + user);
            return "redirect:/login";
        }
        logger.info(new Date() + ": Не удалось выполнить регистрацию пользователя " + usersDataForRegistration);
        return "registration/error";
    }
}

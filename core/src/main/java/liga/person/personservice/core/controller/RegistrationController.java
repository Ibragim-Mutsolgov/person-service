package liga.person.personservice.core.controller;

import liga.person.personservice.core.dto.UsersDataForRegistration;
import liga.person.personservice.core.model.Role;
import liga.person.personservice.core.model.User;
import liga.person.personservice.core.service.RoleService;
import liga.person.personservice.core.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Logger;

@Controller
@AllArgsConstructor
@RequestMapping("/registration")
public class RegistrationController {

    private UserService userService;

    private RoleService roleService;

    private PasswordEncoder passwordEncoder;

    @GetMapping
    public String getPage() {
        Logger logger = Logger.getLogger(RegistrationController.class.getName());
        logger.info("Класс: RegistrationController; метод: getPage()");
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
            role = roleService.save(role);
            Set<Role> roles = new LinkedHashSet<>();
            roles.add(role);
            user.setRoles(roles);
            userService.save(user);
            Logger logger = Logger.getLogger(RegistrationController.class.getName());
            logger.info("Класс: RegistrationController; метод: getRegistration()");
            return "redirect:/login";
        }
        return "registration";
    }
}

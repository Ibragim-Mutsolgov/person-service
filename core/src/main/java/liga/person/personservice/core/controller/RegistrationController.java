package liga.person.personservice.core.controller;

import liga.person.personservice.core.dto.UsersDataForRegistration;
import liga.person.personservice.core.model.Role;
import liga.person.personservice.core.model.User;
import liga.person.personservice.core.repository.RoleRepository;
import liga.person.personservice.core.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Controller
@AllArgsConstructor
@RequestMapping("/registration")
public class RegistrationController {

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder;

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
            return "redirect:/login";
        }
        return "registration/error";
    }
}

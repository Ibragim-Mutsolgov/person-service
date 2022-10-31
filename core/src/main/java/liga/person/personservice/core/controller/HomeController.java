package liga.person.personservice.core.controller;

import liga.person.personservice.core.model.Role;
import liga.person.personservice.core.model.User;
import liga.person.personservice.core.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/")
public class HomeController {

    private UserRepository repository;

    @GetMapping
    public String getPageForUser(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        User user = repository.findByUsername(userDetails.getUsername());
        model.addAttribute("username", user.getUsername());
        for (Role role : user.getRoles()) {
            if (role.getRole().equals("ADMIN")) {
                return "adminPanel";
            }
        }
        return "home";
    }
}

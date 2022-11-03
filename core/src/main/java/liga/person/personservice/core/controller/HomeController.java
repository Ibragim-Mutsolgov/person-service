package liga.person.personservice.core.controller;

import liga.person.personservice.core.model.Role;
import liga.person.personservice.core.model.User;
import liga.person.personservice.core.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.logging.Logger;

@Controller
@RequestMapping("/")
public class HomeController {

    private final UserRepository repository;

    private final Logger logger = Logger.getLogger(HomeController.class.getName());

    public HomeController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String getPageForUser(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        User user = repository.findByUsername(userDetails.getUsername());
        String username = user.getUsername();
        model.addAttribute("username", username);
        for (Role role : user.getRoles()) {
            if (role.getRole().equals("ADMIN")) {
                logger.info(new Date() + ": Администратор " + username + " вошел в систему");
                return "adminPanel";
            }
        }
        logger.info(new Date() + ": Пользователь " + username + " вошел в систему");
        return "home";
    }
}

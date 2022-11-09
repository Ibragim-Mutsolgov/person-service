package liga.person.personservice.core.controller;

import liga.person.personservice.core.model.Role;
import liga.person.personservice.core.model.User;
import liga.person.personservice.core.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.logging.Logger;

@Controller
@AllArgsConstructor
@RequestMapping("/")
public class HomeController {

    private UserService service;

    @GetMapping
    public String getPage(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        User user = service.findByUsername(userDetails.getUsername());
        model.addAttribute("username", user.getUsername());
        Logger logger = Logger.getLogger(HomeController.class.getName());
        logger.info("Класс: HomeController; метод: getPage()");
        for (Role role : user.getRoles()) {
            if (role.getRole().equals("ADMIN")) {
                return "adminPanel";
            }
        }
        return "home";
    }
}

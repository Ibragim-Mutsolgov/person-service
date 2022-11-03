package liga.person.personservice.core.controller;

import liga.person.personservice.core.model.Role;
import liga.person.personservice.core.model.User;
import liga.person.personservice.core.repository.LogsRepository;
import liga.person.personservice.core.repository.UserRepository;
import liga.person.personservice.core.service.SystemSettings;
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

    private LogsRepository logsRepository;

    @GetMapping
    public String getPage(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        User user = repository.findByUsername(userDetails.getUsername());
        model.addAttribute("username", user.getUsername());
        for (Role role : user.getRoles()) {
            if (role.getRole().equals("ADMIN")) {
                SystemSettings.saveToDbAndFile(logsRepository, "Класс HomeController метод getPage(). Администратор вошел в систему", user.getUsername());
                return "adminPanel";
            }
        }
        SystemSettings.saveToDbAndFile(logsRepository, "Класс HomeController метод getPage(). Пользователь вошел в систему", user.getUsername());
        return "home";
    }
}

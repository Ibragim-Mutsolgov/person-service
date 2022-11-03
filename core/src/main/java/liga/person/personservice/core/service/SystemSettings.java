package liga.person.personservice.core.service;

import liga.person.personservice.core.model.Logs;
import liga.person.personservice.core.repository.LogsRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.logging.Logger;

public class SystemSettings {

    public static String getUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        return userDetails.getUsername();
    }

    public static void saveToDbAndFile(LogsRepository repository, String text, String username) {
        Logger logger = Logger.getLogger(SystemSettings.class.getName());
        Logs logs = new Logs(
                new Date(),
                new Date(),
                username,
                text
        );
        logs = repository.save(logs);
        logger.info("Дата: " + logs.getDate() + "; время: " + logs.getTime() + "; " + text + "; имя пользователя: " + username);
    }
}

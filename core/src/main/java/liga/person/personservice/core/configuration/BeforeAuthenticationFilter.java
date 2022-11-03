package liga.person.personservice.core.configuration;

import liga.person.personservice.core.repository.LogsRepository;
import liga.person.personservice.core.service.SystemSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class BeforeAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private LogsRepository repository;

    public BeforeAuthenticationFilter(LogsRepository repository) {
        this.repository = repository;
        super.setUsernameParameter("username");
        super.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        SystemSettings.saveToDbAndFile(repository, "Класс BeforeAuthenticationFilter метод attemptAuthentication(). Попытка войти в систему", username);
        return super.attemptAuthentication(request, response);
    }

    @Override
    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }
}

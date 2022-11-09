package liga.person.personservice.core.configuration;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private DataSource oneDataSource;

    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .jdbcAuthentication().passwordEncoder(passwordEncoder())
                .passwordEncoder(passwordEncoder())
                .dataSource(oneDataSource)
                .usersByUsernameQuery("select username, password, true from users where username=?")
                .authoritiesByUsernameQuery("select role, true from roles where id=(select role_id from users_role where user_id=(select id from users where username=?))");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/login", "/registration").permitAll()
                .anyRequest().fullyAuthenticated()
                .and()
                .addFilterBefore(beforeAuthenticationFilter(), BeforeAuthenticationFilter.class)
                .formLogin().loginPage("/login").usernameParameter("username").defaultSuccessUrl("/").permitAll()
                .and()
                .logout().logoutRequestMatcher(
                        new AntPathRequestMatcher("/logout")
                ).logoutSuccessUrl("/login")
                .and()
                .httpBasic();
    }

    private BeforeAuthenticationFilter beforeAuthenticationFilter() throws Exception {
        BeforeAuthenticationFilter beforeAuthenticationFilter = new BeforeAuthenticationFilter();
        beforeAuthenticationFilter.setAuthenticationManager(authenticationManager());
        beforeAuthenticationFilter.setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
                String username = request.getParameter("username");
                String text = "Класс SecurityConfiguration метод beforeAuthenticationFilter(). Некорректно введены логин или пароль пользователем";
                // SystemSettings.saveToDbAndFile(logsRepository, text, username);
                super.setDefaultFailureUrl("/login?error");
                super.onAuthenticationFailure(request, response, exception);
            }
        });
        return beforeAuthenticationFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

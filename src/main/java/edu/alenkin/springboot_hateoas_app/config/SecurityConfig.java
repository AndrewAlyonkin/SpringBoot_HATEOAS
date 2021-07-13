package edu.alenkin.springboot_hateoas_app.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.alenkin.springboot_hateoas_app.model.AuthUser;
import edu.alenkin.springboot_hateoas_app.model.Role;
import edu.alenkin.springboot_hateoas_app.model.User;
import edu.alenkin.springboot_hateoas_app.repository.UserRepository;
import edu.alenkin.springboot_hateoas_app.utils.JsonUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.Optional;


/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@Configuration
@EnableWebSecurity
@Slf4j
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public static final PasswordEncoder ENCODER = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    private final UserRepository repository;

    private final ObjectMapper objectMapper;

    @PostConstruct
    void setMapper() {
        JsonUtil.setObjectMapper(objectMapper);
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return email -> {
            log.debug("Authentication for {}", email);
            Optional<User> user = repository.findByEmailIgnoreCase(email);
            return new AuthUser(user.orElseThrow(
                    ()->new UsernameNotFoundException("User " + email + " not found")));
        };
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService())
                .passwordEncoder(ENCODER);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/api/account/register").anonymous()
                .antMatchers("/api/account").hasRole(Role.USER.name())
                .antMatchers("/api/**").hasRole(Role.ADMIN.name())
                .and().httpBasic()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().csrf().disable();
    }
}
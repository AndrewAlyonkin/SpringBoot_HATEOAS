package edu.alenkin.springboot_hateoas_app.config;

import edu.alenkin.springboot_hateoas_app.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder())
                .withUser("admin").password("{noop}admin").roles("ADMIN", "USER").and()
                .withUser("user").password("{noop}user").roles("USER");
    }
}

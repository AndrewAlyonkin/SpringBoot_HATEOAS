package edu.alenkin.springboot_hateoas_app;

import edu.alenkin.springboot_hateoas_app.model.Role;
import edu.alenkin.springboot_hateoas_app.model.User;
import edu.alenkin.springboot_hateoas_app.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Set;

@SpringBootApplication
@AllArgsConstructor
public class SpringBootHateoasAppApplication implements ApplicationRunner {
    private final UserRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootHateoasAppApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        repository.save(new User("AFAltair@yandex.ru", "Andrew", "Alenkin", "admin", Set.of(Role.ROLE_ADMIN)));
        repository.save(new User("Irklaser@yandex.ru", "Noname", "Unknown", "user", Set.of(Role.ROLE_USER)));
        repository.findAll().forEach(System.out::println);
    }
}

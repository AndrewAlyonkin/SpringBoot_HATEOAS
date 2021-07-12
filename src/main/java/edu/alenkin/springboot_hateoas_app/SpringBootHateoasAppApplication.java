package edu.alenkin.springboot_hateoas_app;

import edu.alenkin.springboot_hateoas_app.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AllArgsConstructor
public class SpringBootHateoasAppApplication implements ApplicationRunner {
    private final UserRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootHateoasAppApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        repository.findByLastNameContainingIgnoreCase("alenkin").forEach(System.out::println);
    }
}

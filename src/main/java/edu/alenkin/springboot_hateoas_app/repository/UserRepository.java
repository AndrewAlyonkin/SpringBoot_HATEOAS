package edu.alenkin.springboot_hateoas_app.repository;

import edu.alenkin.springboot_hateoas_app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
public interface UserRepository extends JpaRepository<User, Integer> {
}

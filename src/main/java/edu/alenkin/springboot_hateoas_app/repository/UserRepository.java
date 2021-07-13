package edu.alenkin.springboot_hateoas_app.repository;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.transaction.annotation.Transactional;
import edu.alenkin.springboot_hateoas_app.model.User;
import java.util.Optional;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Integer> {

    @RestResource(rel = "by-email", path = "by-email")
    @Query("SELECT u FROM User u WHERE u.email = LOWER(:email)")
    Optional<User> findByEmailIgnoreCase(String email);

    @RestResource(rel = "by-lastname", path = "by-lastname")
    Page<User> findByLastNameContainingIgnoreCase(String lastName, Pageable page);

    @Override
    @Modifying
    @Transactional
    @CachePut(value = "users", key = "#user.email")
    User save(User user);

    @Override
    @Modifying
    @Transactional
    @CacheEvict(value = "users", key = "#user.email")
    void delete(User user);

    @Override
    @Modifying
    @Transactional
    @CacheEvict(value = "users", allEntries = true)
    void deleteById(Integer integer);
}
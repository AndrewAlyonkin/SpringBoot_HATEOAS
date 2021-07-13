package edu.alenkin.springboot_hateoas_app.model;

import lombok.Getter;
import lombok.ToString;
import org.springframework.lang.NonNull;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@Getter
@ToString(of = "user")
public class AuthUser extends org.springframework.security.core.userdetails.User {

    private final User user;

    public AuthUser(@NonNull User user) {
        super(user.getEmail(), user.getPassword(), user.getRoles());
        this.user = user;
    }

    public int id() {
        return user.id();
    }
}
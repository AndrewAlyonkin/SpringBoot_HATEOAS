package edu.alenkin.springboot_hateoas_app.model;

import lombok.Getter;

import javax.validation.constraints.NotNull;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@Getter
public class AuthUser extends org.springframework.security.core.userdetails.User {

    @NotNull
    private User user;

    public AuthUser(User user) {
        super(user.getEmail(), user.getPassword(), user.getRoles());
    }

    public int id(){
        return user.id();
    }
}

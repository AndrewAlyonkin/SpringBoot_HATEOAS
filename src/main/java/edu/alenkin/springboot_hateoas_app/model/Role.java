package edu.alenkin.springboot_hateoas_app.model;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
public enum Role implements GrantedAuthority {
    USER,
    ADMIN;

    @Override
    public String getAuthority() {
        return "ROLE_"+this.name();
    }
}

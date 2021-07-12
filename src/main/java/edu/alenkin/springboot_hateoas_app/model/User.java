package edu.alenkin.springboot_hateoas_app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private Set<Role> roles;

}

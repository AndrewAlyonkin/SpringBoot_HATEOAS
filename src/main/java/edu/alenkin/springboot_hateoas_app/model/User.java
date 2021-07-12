package edu.alenkin.springboot_hateoas_app.model;

import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString(callSuper = true, exclude = {"password"}, includeFieldNames = false)
public class User extends AbstractPersistable<Integer> {

    @Column(name = "email", nullable = false, unique = true)
    @NotNull
    @Email
    @Size(max = 120)
    private String email;

    @Column(name = "firstName")
    @Size(max = 120)
    private String firstName;

    @Column(name = "lastName")
    @Size(max = 120)
    private String lastName;

    @Column(name = "password")
    @Size(max = 120)
    private String password;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> roles;

}

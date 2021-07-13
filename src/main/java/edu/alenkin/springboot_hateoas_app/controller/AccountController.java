package edu.alenkin.springboot_hateoas_app.controller;

import edu.alenkin.springboot_hateoas_app.model.AuthUser;
import edu.alenkin.springboot_hateoas_app.model.Role;
import edu.alenkin.springboot_hateoas_app.model.User;
import edu.alenkin.springboot_hateoas_app.repository.UserRepository;
import edu.alenkin.springboot_hateoas_app.utils.ValidationUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.RepositoryLinksResource;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Set;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@RestController
@RequestMapping(AccountController.URL)
@AllArgsConstructor
@Slf4j
public class AccountController implements RepresentationModelProcessor<RepositoryLinksResource> {

    static final String URL = "/api/account";

    private static final RepresentationModelAssemblerSupport<User, EntityModel<User>> ASSEMBLER =
            new RepresentationModelAssemblerSupport<>(AccountController.class, (Class<EntityModel<User>>) (Class<?>) EntityModel.class) {
                @Override
                public EntityModel<User> toModel(User user) {
                    return EntityModel.of(user, linkTo(AccountController.class).withSelfRel());
                }
            };

    private final UserRepository repository;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<User> get(@AuthenticationPrincipal AuthUser authorized) {
        log.debug("Get user {}", authorized);
        return ASSEMBLER.toModel(authorized.getUser());
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@AuthenticationPrincipal AuthUser user) {
        log.debug("Delete {}", user.getUser());
        repository.deleteById(user.id());
    }

    @PostMapping(value = "/register", consumes = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<EntityModel<User>> register(@RequestBody @Valid User user) {
        log.debug("Create new {}", user);
        ValidationUtil.checkNew(user);
        user.setRoles(Set.of(Role.USER));
        user = repository.save(user);
        URI createdResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/account")
                .build().toUri();
        return ResponseEntity.created(createdResource).body(ASSEMBLER.toModel(user));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody User user, @AuthenticationPrincipal AuthUser authUser) {
        log.debug("Updating user {}", user);
        User old = authUser.getUser();
        ValidationUtil.checkIdConsistance(user, old.getId());
        user.setRoles(old.getRoles());
        if (user.getPassword() == null) {
            user.setPassword(old.getPassword());
        }
        repository.save(user);
    }

    @Override
    public RepositoryLinksResource process(RepositoryLinksResource model) {
        model.add((linkTo(AccountController.class)).withRel("account"));
        return model;
    }

    @GetMapping(value = "/page", produces = MediaTypes.HAL_JSON_VALUE)
    public PagedModel<EntityModel<User>> page(Pageable page, PagedResourcesAssembler<User> pagedAssembler) {
        Page<User> users = repository.findAll(page);
        return pagedAssembler.toModel(users, ASSEMBLER);
    }
}
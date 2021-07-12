package edu.alenkin.springboot_hateoas_app.controller;

import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@RestController
@RequestMapping(value = "/api/account")
public class AccountController {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Object get(@AuthenticationPrincipal Object authorized) {
        return authorized;
    }


}

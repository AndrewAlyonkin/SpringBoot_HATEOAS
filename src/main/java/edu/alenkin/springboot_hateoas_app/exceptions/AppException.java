package edu.alenkin.springboot_hateoas_app.exceptions;

import lombok.Getter;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@Getter
public class AppException extends ResponseStatusException {
    private final ErrorAttributeOptions options;

    public AppException(HttpStatus status, String reason, ErrorAttributeOptions options) {
        super(status, reason);
        this.options = options;
    }
}
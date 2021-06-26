package br.com.devnaweb.fipe.exceptions;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends BaseBusinessException {

    public UserNotFoundException() {
        super(HttpStatus.NOT_FOUND, "User not found", false);
    }
}
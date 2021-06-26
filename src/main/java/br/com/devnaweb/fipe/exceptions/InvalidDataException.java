package br.com.devnaweb.fipe.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidDataException extends BaseBusinessException {

    public InvalidDataException(final String message) {
        super(HttpStatus.BAD_REQUEST, message, false);
    }
}
package br.com.devnaweb.fipe.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidDataException extends BaseBusinessException {

    public InvalidDataException() {
        super(HttpStatus.BAD_REQUEST, "Document or email is invalid", false);
    }
}
package br.com.devnaweb.fipe.controllers;

import br.com.devnaweb.fipe.entities.converters.UsersConverter;
import br.com.devnaweb.fipe.entities.requests.UserRequest;
import br.com.devnaweb.fipe.entities.responses.UserResponse;
import br.com.devnaweb.fipe.usecases.CreateUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/devnaweb/fipe")
public class UsersController {

    @Autowired
    private CreateUser createUser;

    @PostMapping("/user")
    public ResponseEntity<UserResponse> createUser(@RequestBody final UserRequest userRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(UsersConverter.toResponse(createUser.execute(userRequest)));
    }
}

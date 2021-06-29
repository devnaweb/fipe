package br.com.devnaweb.fipe.fixtures;

import br.com.devnaweb.fipe.entities.User;
import br.com.devnaweb.fipe.entities.requests.UserRequest;
import br.com.devnaweb.fipe.entities.responses.UserResponse;

import java.util.Collections;

public class UserFixture {

    public static User DEFAULT_USER() {
        return User.builder()
                .name("Devnaweb")
                .email("devnaweb@devnaweb.com")
                .birthDate("11/11/1997")
                .document("01234567890")
                .vehicles(Collections.singletonList(VehicleFixture.DEFAULT_VEHICLE()))
                .build();
    }

    public static UserResponse DEFAULT_USER_RESPONSE() {
        return UserResponse.builder()
                .name("Devnaweb")
                .email("devnaweb@devnaweb.com")
                .birthDate("11/11/1997")
                .document("01234567890")
                .build();
    }

    public static UserRequest DEFAULT_USER_REQUEST() {
        return UserRequest.builder()
                .name("Devnaweb")
                .email("devnaweb@devnaweb.com")
                .birthDate("11/11/1997")
                .document("01234567890")
                .build();
    }
}

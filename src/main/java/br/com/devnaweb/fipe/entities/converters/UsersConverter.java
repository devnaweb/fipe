package br.com.devnaweb.fipe.entities.converters;

import br.com.devnaweb.fipe.entities.User;
import br.com.devnaweb.fipe.entities.responses.UserResponse;

public class UsersConverter {
    public static UserResponse toResponse(final User user) {
        return UserResponse.builder()
                .document(user.getDocument())
                .birthDate(user.getBirthDate())
                .email(user.getEmail())
                .name(user.getName())
                .build();
    }
}

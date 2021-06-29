package br.com.devnaweb.fipe.usecases;

import br.com.devnaweb.fipe.entities.User;
import br.com.devnaweb.fipe.entities.requests.UserRequest;
import br.com.devnaweb.fipe.exceptions.InvalidDataException;
import br.com.devnaweb.fipe.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateUser {

    @Autowired
    private UserRepository userRepository;

    public User execute(final UserRequest userRequest) {
        validateUserEmail(userRequest.getEmail());
        validateUserDocument(userRequest.getDocument());

        return userRepository.save(User.builder()
                .document(userRequest.getDocument())
                .birthDate(userRequest.getBirthDate())
                .email(userRequest.getEmail())
                .name(userRequest.getName())
                .build());
    }

    private void validateUserDocument(final String document) {
        if (userRepository.findUserByDocument(document).isPresent()) {
            throw new InvalidDataException("Document or email is invalid");
        }
    }

    private void validateUserEmail(final String email) {
        if (userRepository.findUserByEmail(email).isPresent()) {
            throw new InvalidDataException("Document or email is invalid");
        }
    }
}

package br.com.devnaweb.fipe.usecases;

import br.com.devnaweb.fipe.entities.User;
import br.com.devnaweb.fipe.exceptions.UserNotFoundException;
import br.com.devnaweb.fipe.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ListUserVehicles {

    @Autowired
    private UserRepository userRepository;

    public User execute(final String document) {
        final Optional<User> savedUser = userRepository.findUserByDocument(document);
        if (savedUser.isPresent()) {
            return savedUser.get();
        }
        throw new UserNotFoundException();
    }
}

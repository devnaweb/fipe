package br.com.devnaweb.fipe.usecases;

import br.com.devnaweb.fipe.entities.User;
import br.com.devnaweb.fipe.entities.requests.UserRequest;
import br.com.devnaweb.fipe.exceptions.InvalidDataException;
import br.com.devnaweb.fipe.fixtures.UserFixture;
import br.com.devnaweb.fipe.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CreateUserUnitTest {

    @InjectMocks
    private CreateUser createUser;

    @Mock
    private UserRepository userRepository;

    @Test
    void shouldCreateUser() {
        when(userRepository.findUserByDocument(any())).thenReturn(Optional.empty());
        when(userRepository.findUserByEmail(any())).thenReturn(Optional.empty());
        when(userRepository.save(any())).thenReturn(UserFixture.DEFAULT_USER());
        final UserRequest userRequest = UserFixture.DEFAULT_USER_REQUEST();
        final User user = createUser.execute(userRequest);

        assertNotNull(user);
        assertThat(user.getBirthDate(), is(userRequest.getBirthDate()));
        assertThat(user.getDocument(), is(userRequest.getDocument()));
        assertThat(user.getEmail(), is(userRequest.getEmail()));
        assertThat(user.getName(), is(userRequest.getName()));
    }

    @Test
    void shouldFailCreateUserWhenDocumentAlreadyExists() {
        when(userRepository.findUserByDocument(any())).thenReturn(Optional.of(UserFixture.DEFAULT_USER()));

        assertThrows(InvalidDataException.class, () -> {
            createUser.execute(UserFixture.DEFAULT_USER_REQUEST());
        });
    }

    @Test
    void shouldFailCreateUserWhenEmailAlreadyExists() {
        when(userRepository.findUserByEmail(any())).thenReturn(Optional.of(UserFixture.DEFAULT_USER()));

        assertThrows(InvalidDataException.class, () -> {
            createUser.execute(UserFixture.DEFAULT_USER_REQUEST());
        });
    }
}

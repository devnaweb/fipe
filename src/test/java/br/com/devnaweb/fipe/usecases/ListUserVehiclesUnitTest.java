package br.com.devnaweb.fipe.usecases;

import br.com.devnaweb.fipe.entities.User;
import br.com.devnaweb.fipe.exceptions.UserNotFoundException;
import br.com.devnaweb.fipe.fixtures.UserFixture;
import br.com.devnaweb.fipe.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ListUserVehiclesUnitTest {

    @InjectMocks
    private ListUserVehicles listUserVehicles;

    @Mock
    private UserRepository userRepository;

    @Test
    void shouldListUserVehicles() {
        final User user = UserFixture.DEFAULT_USER();
        when(userRepository.findUserByDocument(any())).thenReturn(Optional.of(user));

        final User savedUser = listUserVehicles.execute(user.getDocument());

        assertNotNull(savedUser);
        assertThat(savedUser.getName(), is(user.getName()));
        assertThat(savedUser.getBirthDate(), is(user.getBirthDate()));
        assertThat(savedUser.getDocument(), is(user.getDocument()));
        assertThat(savedUser.getEmail(), is(user.getEmail()));
        assertThat(savedUser.getVehicles().size(), is(user.getVehicles().size()));
        assertThat(savedUser.getVehicles().get(0).getBrand(), is(user.getVehicles().get(0).getBrand()));
        assertThat(savedUser.getVehicles().get(0).getModel(), is(user.getVehicles().get(0).getModel()));
        assertThat(savedUser.getVehicles().get(0).getType(), is(user.getVehicles().get(0).getType()));
        assertThat(savedUser.getVehicles().get(0).getValue(), is(user.getVehicles().get(0).getValue()));
        assertThat(savedUser.getVehicles().get(0).getRestrictionDay(), is(user.getVehicles().get(0).getRestrictionDay()));
    }

    @Test
    void shouldFailListUserVehiclesWhenUserNotFound() {
        when(userRepository.findUserByDocument(any())).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> {
            listUserVehicles.execute("123");
        });
    }
}

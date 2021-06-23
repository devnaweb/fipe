package br.com.devnaweb.fipe.entities.converters;

import br.com.devnaweb.fipe.entities.User;
import br.com.devnaweb.fipe.entities.responses.UserResponse;
import br.com.devnaweb.fipe.fixtures.UserFixture;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UsersConverterUnitTest {

    @Test
    void shouldConvertUserToUserResponse() {
        final User user = UserFixture.DEFAULT_USER();

        final UserResponse userResponse = UsersConverter.toResponse(user);

        assertNotNull(userResponse);
        assertThat(userResponse.getDocument(), is(user.getDocument()));
        assertThat(userResponse.getBirthDate(), is(user.getBirthDate()));
        assertThat(userResponse.getEmail(), is(user.getEmail()));
        assertThat(userResponse.getName(), is(user.getName()));
    }
}

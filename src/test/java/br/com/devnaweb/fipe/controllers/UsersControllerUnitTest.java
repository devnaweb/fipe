package br.com.devnaweb.fipe.controllers;

import br.com.devnaweb.fipe.entities.User;
import br.com.devnaweb.fipe.entities.requests.UserRequest;
import br.com.devnaweb.fipe.exceptions.InvalidDataException;
import br.com.devnaweb.fipe.fixtures.UserFixture;
import br.com.devnaweb.fipe.usecases.CreateUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.web.util.NestedServletException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class UsersControllerUnitTest extends AbstractControllerTest {

    @InjectMocks
    private UsersController usersController;

    @Mock
    private CreateUser createUser;

    @BeforeEach
    void setUp() {
        this.setUp(usersController);
    }

    @Test
    void shouldCreateUser() throws Exception {
        final User user = UserFixture.DEFAULT_USER();
        when(createUser.execute(any())).thenReturn(user);

        mockMvc.perform(post("/api/v1/devnaweb/fipe/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapToJson(user))
                .characterEncoding("utf-8"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.document").value(user.getDocument()))
                .andExpect(jsonPath("$.name").value(user.getName()))
                .andExpect(jsonPath("$.email").value(user.getEmail()))
                .andExpect(jsonPath("$.birthDate").value(user.getBirthDate()))
                .andReturn();

        verify(createUser).execute(any());
    }

    @Test
    void shouldFailCreateUserWhenDocumentOrEmailAlreadyExists() {
        final UserRequest user = UserFixture.DEFAULT_USER_REQUEST();
        when(createUser.execute(user)).thenThrow(InvalidDataException.class);

        assertThrows(NestedServletException.class, () -> {
            mockMvc.perform(post("/api/v1/devnaweb/fipe/user")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapToJson(user))
                    .characterEncoding("utf-8"))
                    .andReturn();
        });
    }

    @Test
    void shouldFailWhenDataIsInvalid() {
        final User user = UserFixture.DEFAULT_USER().toBuilder().name(null).build();

        assertThrows(NestedServletException.class, () -> {
            mockMvc.perform(post("/api/v1/devnaweb/fipe/user")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapToJson(user))
                    .characterEncoding("utf-8"))
                    .andReturn();
        });
    }
}

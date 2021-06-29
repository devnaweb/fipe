package br.com.devnaweb.fipe.controllers;

import br.com.devnaweb.fipe.entities.User;
import br.com.devnaweb.fipe.entities.Vehicle;
import br.com.devnaweb.fipe.entities.requests.VehicleRequest;
import br.com.devnaweb.fipe.exceptions.UserNotFoundException;
import br.com.devnaweb.fipe.fixtures.UserFixture;
import br.com.devnaweb.fipe.fixtures.VehicleFixture;
import br.com.devnaweb.fipe.usecases.CreateVehicle;
import br.com.devnaweb.fipe.usecases.ListUserVehicles;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class VehiclesControllerUnitTest extends AbstractControllerTest {

    @InjectMocks
    private VehiclesController vehiclesController;

    @Mock
    private CreateVehicle createVehicle;

    @Mock
    private ListUserVehicles listUserVehicles;

    @BeforeEach
    void setUp() {
        this.setUp(vehiclesController);
    }

    @Test
    void shouldCreateVehicle() throws Exception {
        final Vehicle vehicle = VehicleFixture.DEFAULT_VEHICLE();
        when(createVehicle.execute(any(), any())).thenReturn(vehicle);

        mockMvc.perform(post("/api/v1/devnaweb/fipe/vehicle/" + vehicle.getDocument())
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapToJson(vehicle))
                .characterEncoding("utf-8"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.brand").value(vehicle.getBrand()))
                .andExpect(jsonPath("$.model").value(vehicle.getModel()))
                .andExpect(jsonPath("$.value").value(vehicle.getValue()))
                .andExpect(jsonPath("$.type").value(vehicle.getType().getValue()))
                .andExpect(jsonPath("$.year").value(vehicle.getYear()))
                .andReturn();

        verify(createVehicle).execute(any(), any());
    }

    @Test
    void shouldFailCreateVehicleWhenUserNotFound() {
        final VehicleRequest vehicleRequest = VehicleFixture.DEFAULT_VEHICLE_REQUEST();
        when(createVehicle.execute(any(), any())).thenThrow(UserNotFoundException.class);

        assertThrows(NestedServletException.class, () -> {
            mockMvc.perform(post("/api/v1/devnaweb/fipe/vehicle/0123")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapToJson(vehicleRequest))
                    .characterEncoding("utf-8"))
                    .andReturn();
        });
    }

    @Test
    void shouldFailCreateVehicleWhenTypeIsInvalid() {
        assertThrows(NestedServletException.class, () -> {
            mockMvc.perform(post("/api/v1/devnaweb/fipe/vehicle/0123")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapToJson(VehicleFixture.DEFAULT_VEHICLE_REQUEST().toBuilder().type(null).build()))
                    .characterEncoding("utf-8"))
                    .andReturn();
        });
    }

    @Test
    void shouldListUsersVehicle() throws Exception {
        final User user = UserFixture.DEFAULT_USER();
        when(listUserVehicles.execute(any())).thenReturn(user);

        mockMvc.perform(get("/api/v1/devnaweb/fipe/vehicle/123")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.document").value(user.getDocument()))
                .andExpect(jsonPath("$.name").value(user.getName()))
                .andExpect(jsonPath("$.email").value(user.getEmail()))
                .andExpect(jsonPath("$.birthDate").value(user.getBirthDate()))
                .andReturn();

        verify(listUserVehicles).execute(any());
    }

    @Test
    void shouldFailListUsersVehicleWhenUserNotFound() {
        when(createVehicle.execute(any(), any())).thenThrow(UserNotFoundException.class);

        assertThrows(NestedServletException.class, () -> {
            mockMvc.perform(get("/api/v1/devnaweb/fipe/vehicle/0123")
                    .contentType(MediaType.APPLICATION_JSON)
                    .characterEncoding("utf-8"))
                    .andReturn();
        });
    }
}

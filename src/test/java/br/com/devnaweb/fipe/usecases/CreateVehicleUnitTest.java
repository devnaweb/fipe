package br.com.devnaweb.fipe.usecases;

import br.com.devnaweb.fipe.entities.Vehicle;
import br.com.devnaweb.fipe.exceptions.UserNotFoundException;
import br.com.devnaweb.fipe.fixtures.UserFixture;
import br.com.devnaweb.fipe.fixtures.VehicleFixture;
import br.com.devnaweb.fipe.repositories.UserRepository;
import br.com.devnaweb.fipe.repositories.VehicleRepository;
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
public class CreateVehicleUnitTest {

    @InjectMocks
    private CreateVehicle createVehicle;

    @Mock
    private UserRepository userRepository;

    @Mock
    private VehicleRepository vehicleRepository;

    @Test
    void shouldCreateVehicle() {
        final Vehicle vehicle = VehicleFixture.DEFAULT_VEHICLE();
        when(userRepository.findUserByDocument(any())).thenReturn(Optional.of(UserFixture.DEFAULT_USER()));
        when(vehicleRepository.save(any())).thenReturn(vehicle);

        final Vehicle savedVehicle = createVehicle.execute(VehicleFixture.DEFAULT_VEHICLE_REQUEST(), "123");

        assertNotNull(savedVehicle);
        assertThat(savedVehicle.getBrand(), is(vehicle.getBrand()));
        assertThat(savedVehicle.getModel(), is(vehicle.getModel()));
        assertThat(savedVehicle.getDocument(), is(vehicle.getDocument()));
        assertThat(savedVehicle.getYear(), is(vehicle.getYear()));
        assertThat(savedVehicle.getValue(), is(vehicle.getValue()));
        assertThat(savedVehicle.getType(), is(vehicle.getType()));
    }

    @Test
    void shouldFailCreateVehicleWhenUserNotFound() {
        when(userRepository.findUserByDocument(any())).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> {
            createVehicle.execute(VehicleFixture.DEFAULT_VEHICLE_REQUEST(), "123");
        });
    }
}

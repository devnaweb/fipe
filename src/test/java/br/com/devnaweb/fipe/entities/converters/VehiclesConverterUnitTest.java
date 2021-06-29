package br.com.devnaweb.fipe.entities.converters;

import br.com.devnaweb.fipe.entities.User;
import br.com.devnaweb.fipe.entities.Vehicle;
import br.com.devnaweb.fipe.entities.responses.UserVehiclesResponse;
import br.com.devnaweb.fipe.entities.responses.VehicleResponse;
import br.com.devnaweb.fipe.fixtures.UserFixture;
import br.com.devnaweb.fipe.fixtures.VehicleFixture;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class VehiclesConverterUnitTest {

    @Test
    void shouldConvertVehicleToResponse() {
        final Vehicle vehicle = VehicleFixture.DEFAULT_VEHICLE();

        final VehicleResponse vehicleResponse = VehiclesConverter.toResponse(vehicle);

        assertNotNull(vehicleResponse);
        assertThat(vehicleResponse.getBrand(), is(vehicle.getBrand()));
        assertThat(vehicleResponse.getModel(), is(vehicle.getModel()));
        assertThat(vehicleResponse.getType(), is(vehicle.getType()));
        assertThat(vehicleResponse.getYear(), is(vehicle.getYear()));
        assertThat(vehicleResponse.getValue(), is(vehicle.getValue()));
    }

    @Test
    void shouldConvertUserVehiclesResponse() {
        final User user = UserFixture.DEFAULT_USER();

        final UserVehiclesResponse userVehiclesResponse = VehiclesConverter.toUserVehiclesResponse(user);

        assertNotNull(userVehiclesResponse);
        assertThat(userVehiclesResponse.getDocument(), is(user.getDocument()));
        assertThat(userVehiclesResponse.getEmail(), is(user.getEmail()));
        assertThat(userVehiclesResponse.getBirthDate(), is(user.getBirthDate()));
        assertThat(userVehiclesResponse.getName(), is(user.getName()));
        assertThat(userVehiclesResponse.getVehicles().size(), is(user.getVehicles().size()));
        assertThat(userVehiclesResponse.getVehicles().get(0).getBrand(), is(user.getVehicles().get(0).getBrand()));
        assertThat(userVehiclesResponse.getVehicles().get(0).getValue(), is(user.getVehicles().get(0).getValue()));
        assertThat(userVehiclesResponse.getVehicles().get(0).getRestrictionDay(), is(user.getVehicles().get(0).getRestrictionDay()));
        assertThat(userVehiclesResponse.getVehicles().get(0).getYear(), is(user.getVehicles().get(0).getYear()));
        assertThat(userVehiclesResponse.getVehicles().get(0).getType(), is(user.getVehicles().get(0).getType()));
        assertThat(userVehiclesResponse.getVehicles().get(0).getModel(), is(user.getVehicles().get(0).getModel()));
    }
}

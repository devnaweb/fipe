package br.com.devnaweb.fipe.entities.converters;

import br.com.devnaweb.fipe.entities.User;
import br.com.devnaweb.fipe.entities.Vehicle;
import br.com.devnaweb.fipe.entities.responses.UserVehiclesResponse;
import br.com.devnaweb.fipe.entities.responses.VehicleResponse;
import br.com.devnaweb.fipe.utils.RestrictionDayUtil;

import java.util.stream.Collectors;

public class VehiclesConverter {
    public static VehicleResponse toResponse(final Vehicle vehicle) {
        return VehicleResponse.builder()
                .brand(vehicle.getBrand())
                .model(vehicle.getModel())
                .type(vehicle.getType())
                .year(vehicle.getYear())
                .value(vehicle.getValue())
                .restrictionDay(vehicle.getRestrictionDay())
                .isRestrictionDay(RestrictionDayUtil.validateRestrictionDay(vehicle.getRestrictionDay().getValue()))
                .build();
    }

    public static UserVehiclesResponse toUserVehiclesResponse(final User user) {
        return UserVehiclesResponse.builder()
                .name(user.getName())
                .email(user.getEmail())
                .document(user.getDocument())
                .birthDate(user.getBirthDate())
                .vehicles(user.getVehicles().stream().map(vehicle -> VehicleResponse.builder()
                        .brand(vehicle.getBrand())
                        .model(vehicle.getModel())
                        .year(vehicle.getYear())
                        .type(vehicle.getType())
                        .isRestrictionDay(
                                RestrictionDayUtil.validateRestrictionDay(
                                        vehicle.getRestrictionDay().getValue()))
                        .value(vehicle.getValue())
                        .restrictionDay(vehicle.getRestrictionDay())
                        .build()).collect(Collectors.toList()))
                .build();
    }
}

package br.com.devnaweb.fipe.fixtures;

import br.com.devnaweb.fipe.entities.Vehicle;
import br.com.devnaweb.fipe.entities.enums.RestrictionDay;
import br.com.devnaweb.fipe.entities.enums.VehicleType;
import br.com.devnaweb.fipe.entities.requests.VehicleRequest;
import br.com.devnaweb.fipe.entities.responses.VehicleResponse;

public class VehicleFixture {

    public static Vehicle DEFAULT_VEHICLE() {
        return Vehicle.builder()
                .document("01234567890")
                .value("10000")
                .year("2022")
                .type(VehicleType.CAR)
                .model("CIVIC")
                .brand("HONDA")
                .id(1L)
                .restrictionDay(RestrictionDay.FRIDAY)
                .build();
    }

    public static VehicleRequest DEFAULT_VEHICLE_REQUEST() {
        return VehicleRequest.builder()
                .year("2022")
                .type(VehicleType.CAR)
                .model("CIVIC")
                .brand("HONDA")
                .build();
    }

    public static VehicleResponse DEFAULT_VEHICLE_RESPONSE() {
        return VehicleResponse.builder()
                .value("10000")
                .year("2022")
                .type(VehicleType.CAR)
                .model("CIVIC")
                .brand("HONDA")
                .build();
    }
}

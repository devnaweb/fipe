package br.com.devnaweb.fipe.entities.enums;

import br.com.devnaweb.fipe.exceptions.InvalidDataException;
import com.fasterxml.jackson.annotation.JsonCreator;

public enum VehicleType {

    CAR("CAR"),
    MOTORCYCLE("MOTORCYCLE"),
    TRUCK("TRUCK");

    private final String value;

    VehicleType(final String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    @JsonCreator
    public static VehicleType fromValue(final String text) {
        for (VehicleType tt : VehicleType.values()) {
            if (String.valueOf(tt.value).equalsIgnoreCase(text)) {
                return tt;
            }
        }
        throw new InvalidDataException("Type is invalid");
    }

}

package br.com.devnaweb.fipe.entities.enums;

import br.com.devnaweb.fipe.exceptions.InvalidDataException;
import com.fasterxml.jackson.annotation.JsonCreator;

public enum RestrictionDay {

    MONDAY("MONDAY"),
    TUESDAY("TUESDAY"),
    WEDNESDAY("WEDNESDAY"),
    THURSDAY("THURSDAY"),
    FRIDAY("FRIDAY");

    private final String value;

    RestrictionDay(final String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}

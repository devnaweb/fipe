package br.com.devnaweb.fipe.utils;

import br.com.devnaweb.fipe.entities.enums.RestrictionDay;

import java.time.LocalDateTime;

public class RestrictionDayUtil {

    public static final int LAST_DIGIT = 3;

    public static boolean validateRestrictionDay(final String restrictionDay) {
        return restrictionDay.equalsIgnoreCase(LocalDateTime.now().getDayOfWeek().name());
    }

    public static RestrictionDay getRestrictionDay(final String year) {
        switch (year.substring(LAST_DIGIT)) {
            case "0":
            case "1":
                return RestrictionDay.MONDAY;
            case "2":
            case "3":
                return RestrictionDay.TUESDAY;
            case "4":
            case "5":
                return RestrictionDay.WEDNESDAY;
            case "6":
            case "7":
                return RestrictionDay.THURSDAY;
            default:
                return RestrictionDay.FRIDAY;
        }
    }
}

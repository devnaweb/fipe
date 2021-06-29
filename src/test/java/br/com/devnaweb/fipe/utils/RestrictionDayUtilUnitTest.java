package br.com.devnaweb.fipe.utils;

import br.com.devnaweb.fipe.entities.enums.RestrictionDay;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class RestrictionDayUtilUnitTest {

    @Test
    void shouldValidateRestrictionDay() {
        assertFalse(RestrictionDayUtil.validateRestrictionDay("MONDAY"));
    }

    @Test
    void shouldGetRestrictionDayWhenMonday() {
        final String year = "2021";
        assertThat(RestrictionDayUtil.getRestrictionDay(year), is(RestrictionDay.MONDAY));
    }

    @Test
    void shouldGetRestrictionDayWhenTuesday() {
        final String year = "2023";
        assertThat(RestrictionDayUtil.getRestrictionDay(year), is(RestrictionDay.TUESDAY));
    }

    @Test
    void shouldGetRestrictionDayWhenWednesday() {
        final String year = "2025";
        assertThat(RestrictionDayUtil.getRestrictionDay(year), is(RestrictionDay.WEDNESDAY));
    }

    @Test
    void shouldGetRestrictionDayWhenThursday() {
        final String year = "2027";
        assertThat(RestrictionDayUtil.getRestrictionDay(year), is(RestrictionDay.THURSDAY));
    }

    @Test
    void shouldGetRestrictionDayWhenFriday() {
        final String year = "2029";
        assertThat(RestrictionDayUtil.getRestrictionDay(year), is(RestrictionDay.FRIDAY));
    }
}

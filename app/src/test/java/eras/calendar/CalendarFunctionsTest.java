package eras.calendar;

import eras.Ted;
import org.junit.Test;

import java.util.List;

import static eras.checker.Checker.*;

public class CalendarFunctionsTest extends Ted {
    YearElement normalMonth = YearElement.Month.of("June", 30);
    YearElement withLeapDay = YearElement.Month.of(
        "February", 28, LeapDays.GREGORIAN);
    YearElement epagomenal = YearElement.Epagomenal.of(
        "EndDays", 5, LeapDays.JULIAN);

    @Test public void testLengthOfYearElementInDays() {
        title("testLengthOfYearElementInDays");

        require(CalendarFunctions.lengthOfYearElementInDays(normalMonth, 2019), eq(30));
        require(CalendarFunctions.lengthOfYearElementInDays(normalMonth, 2020), eq(30));

        require(CalendarFunctions.lengthOfYearElementInDays(withLeapDay, 2019), eq(28));
        require(CalendarFunctions.lengthOfYearElementInDays(withLeapDay, 2020), eq(29));

        require(CalendarFunctions.lengthOfYearElementInDays(epagomenal, 2099), eq(5));
        require(CalendarFunctions.lengthOfYearElementInDays(epagomenal, 2100), eq(6));
    }

    @Test public void testLengthOfYearInDays() {
        title("testLengthOfYearInDays");

        Calendar cycle = new Calendar(List.of(
            withLeapDay,
            normalMonth,
            epagomenal));

        // No leap days
        require(CalendarFunctions.lengthOfYearInDays(cycle, 2019), eq(63));

        // February and "End Days" leap days
        require(CalendarFunctions.lengthOfYearInDays(cycle, 2020), eq(65));

        // Only "End Days" leap day
        require(CalendarFunctions.lengthOfYearInDays(cycle, 2100), eq(64));

    }
}

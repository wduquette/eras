package eras.calendar;

import eras.Ted;
import org.junit.Test;

import static eras.checker.Checker.*;

public class CalendarsTest extends Ted {
    YearElement normalMonth = YearElement.Month.of("June", 30);
    YearElement withLeapDay = YearElement.Month.of(
        "February", 28, LeapRule.GREGORIAN);
    YearElement epagomenal = YearElement.Epagomenal.of(
        "EndDays", 5, LeapRule.JULIAN);

    @Test public void testNumberOfLeapDays() {
        title("testNumberOfLeapDays");

        // Note: for era XY, the years 1,2,3,... BXY are coded 0, -1, -2,...

        // LeapRule.None
        require(Calendars.numberOfLeapDays(LeapRule.NONE, -1), eq(0));
        require(Calendars.numberOfLeapDays(LeapRule.NONE, 0), eq(0));
        require(Calendars.numberOfLeapDays(LeapRule.NONE, 1), eq(0));

        // LeapRule.EveryN, N = 4
        require(Calendars.numberOfLeapDays(LeapRule.JULIAN, -100), eq(1));
        require(Calendars.numberOfLeapDays(LeapRule.JULIAN, -4), eq(1));
        require(Calendars.numberOfLeapDays(LeapRule.JULIAN, -1), eq(0));
        require(Calendars.numberOfLeapDays(LeapRule.JULIAN, 0), eq(1));
        require(Calendars.numberOfLeapDays(LeapRule.JULIAN, 1), eq(0));
        require(Calendars.numberOfLeapDays(LeapRule.JULIAN, 4), eq(1));
        require(Calendars.numberOfLeapDays(LeapRule.JULIAN, 100), eq(1));

        // LeapRule.Gregorian
        require(Calendars.numberOfLeapDays(LeapRule.GREGORIAN, -400), eq(1));
        require(Calendars.numberOfLeapDays(LeapRule.GREGORIAN, -100), eq(0));
        require(Calendars.numberOfLeapDays(LeapRule.GREGORIAN, -4), eq(1));
        require(Calendars.numberOfLeapDays(LeapRule.GREGORIAN, -1), eq(0));
        require(Calendars.numberOfLeapDays(LeapRule.GREGORIAN, 0), eq(1));
        require(Calendars.numberOfLeapDays(LeapRule.GREGORIAN, 1), eq(0));
        require(Calendars.numberOfLeapDays(LeapRule.GREGORIAN, 4), eq(1));
        require(Calendars.numberOfLeapDays(LeapRule.GREGORIAN, 100), eq(0));
        require(Calendars.numberOfLeapDays(LeapRule.GREGORIAN, 400), eq(1));
    }


    @Test public void testLengthOfYearElementInDays() {
        title("testLengthOfYearElementInDays");

        require(Calendars.lengthOfYearElementInDays(normalMonth, 2019), eq(30));
        require(Calendars.lengthOfYearElementInDays(normalMonth, 2020), eq(30));

        require(Calendars.lengthOfYearElementInDays(withLeapDay, 2019), eq(28));
        require(Calendars.lengthOfYearElementInDays(withLeapDay, 2020), eq(29));

        require(Calendars.lengthOfYearElementInDays(epagomenal, 2099), eq(5));
        require(Calendars.lengthOfYearElementInDays(epagomenal, 2100), eq(6));
    }

    @Test public void testLengthOfYearInDays() {
        title("testLengthOfYearInDays");

        CalendarSchema schema = CalendarSchema.of(
            withLeapDay,
            normalMonth,
            epagomenal);

        // No leap days
        require(Calendars.lengthOfYearInDays(schema, 2019), eq(63));

        // February and "End Days" leap days
        require(Calendars.lengthOfYearInDays(schema, 2020), eq(65));

        // Only "End Days" leap day
        require(Calendars.lengthOfYearInDays(schema, 2100), eq(64));
    }
}

package eras.basics;

import eras.Ted;
import org.junit.Test;

import java.util.List;

import static eras.checker.Checker.*;

public class BasicsTest extends Ted {
    YearElement normalMonth = YearElement.Month.of("June", 30);
    YearElement withLeapDay = YearElement.Month.of(
        "February", 28, LeapDays.GREGORIAN);
    YearElement epagomenal = YearElement.Epagomenal.of(
        "EndDays", 5, LeapDays.JULIAN);

    @Test public void testLengthOfYearElementInDays() {
        title("testLengthOfYearElementInDays");

        require(Basics.lengthOfYearElementInDays(normalMonth, 2019), eq(30));
        require(Basics.lengthOfYearElementInDays(normalMonth, 2020), eq(30));

        require(Basics.lengthOfYearElementInDays(withLeapDay, 2019), eq(28));
        require(Basics.lengthOfYearElementInDays(withLeapDay, 2020), eq(29));

        require(Basics.lengthOfYearElementInDays(epagomenal, 2099), eq(5));
        require(Basics.lengthOfYearElementInDays(epagomenal, 2100), eq(6));
    }

    @Test public void testLengthOfYearInDays() {
        title("testLengthOfYearInDays");

        YearlyCycle cycle = new YearlyCycle(List.of(
            withLeapDay,
            normalMonth,
            epagomenal));

        // No leap days
        require(Basics.lengthOfYearInDays(cycle, 2019), eq(63));

        // February and "End Days" leap days
        require(Basics.lengthOfYearInDays(cycle, 2020), eq(65));

        // Only "End Days" leap day
        require(Basics.lengthOfYearInDays(cycle, 2100), eq(64));

    }
}

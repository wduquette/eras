package eras.basics;

import eras.Ted;
import org.junit.Test;

import java.util.List;

import static eras.checker.Checker.*;

public class BasicsTest extends Ted {
    @Test public void testLengthOfYearElementInDays() {
        title("testLengthOfYearElementInDays");
        YearElement normalMonth = new YearElement.Month("June", 30);
        YearElement withLeapDay = new YearElement.MonthWithLeapDay(
            "February", 28, new LeapRule.Gregorian());

        require(Basics.lengthOfYearElementInDays(normalMonth, 2019), eq(30));
        require(Basics.lengthOfYearElementInDays(normalMonth, 2020), eq(30));

        require(Basics.lengthOfYearElementInDays(withLeapDay, 2019), eq(28));
        require(Basics.lengthOfYearElementInDays(withLeapDay, 2020), eq(29));
    }

    @Test public void testLengthOfYearInDays() {
        title("testLengthOfYearInDays");

        YearElement normalMonth = new YearElement.Month("June", 30);
        YearElement withLeapDay = new YearElement.MonthWithLeapDay(
            "February", 28, new LeapRule.Gregorian());
        YearlyCycle cycle = new YearlyCycle(List.of(withLeapDay, normalMonth));

        require(Basics.lengthOfYearInDays(cycle, 2019), eq(58));
        require(Basics.lengthOfYearInDays(cycle, 2020), eq(59));

    }
}

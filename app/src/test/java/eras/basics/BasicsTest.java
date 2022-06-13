package eras.basics;

import eras.Ted;
import org.junit.Test;
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
}

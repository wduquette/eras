package eras.calendar;

import eras.Ted;
import org.junit.Test;

import static eras.checker.Checker.*;

public class LeapDaysTest extends Ted {
    LeapDays gregorian = new LeapDays.Gregorian();
    LeapDays every5 = new LeapDays.EveryN(5);

    @Test public void testGregorian() {
        title("testGregorian");

        require(gregorian.forYear(2000), eq(1));
        require(gregorian.forYear(1900), eq(0));
        require(gregorian.forYear(2004), eq(1));
        require(gregorian.forYear(2005), eq(0));
    }

    @Test public void testEveryN() {
        title("testEveryN");

        require(every5.forYear(2000), eq(1));
        require(every5.forYear(2001), eq(0));
        require(every5.forYear(2005), eq(1));
    }
}

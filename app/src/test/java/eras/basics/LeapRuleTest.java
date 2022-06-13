package eras.basics;

import eras.Ted;
import org.junit.Test;

import static eras.checker.Checker.*;

public class LeapRuleTest extends Ted {
    LeapRule gregorian = new LeapRule.Gregorian();
    LeapRule every5 = new LeapRule.EveryN(5);

    @Test public void testGregorian() {
        title("testGregorian");

        require(gregorian.leapDays(2000), eq(1));
        require(gregorian.leapDays(1900), eq(0));
        require(gregorian.leapDays(2004), eq(1));
        require(gregorian.leapDays(2005), eq(0));
    }

    @Test public void testEveryN() {
        title("testEveryN");

        require(every5.leapDays(2000), eq(1));
        require(every5.leapDays(2001), eq(0));
        require(every5.leapDays(2005), eq(1));
    }
}

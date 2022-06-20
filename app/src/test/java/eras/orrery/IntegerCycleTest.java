package eras.orrery;

import eras.Ted;
import org.junit.Test;

import static eras.checker.Checker.*;

public class IntegerCycleTest extends Ted {
    // Day 0 is a Tuesday
    IntegerCycle cycle = new IntegerCycle(7, 2);

    @Test public void testBasics() {
        title("testBasics");

        require(cycle.length(), eq(7));
        require(cycle.startDay(), eq(2));
    }

    @Test public void testCycleCount() {
        title("testCycleCount");

        require(cycle.cycleCount(0), eq(0));
        require(cycle.cycleCount(6), eq(0));
        require(cycle.cycleCount(7), eq(1));
        require(cycle.cycleCount(13), eq(1));
        require(cycle.cycleCount(14), eq(2));

        require(cycle.cycleCount(-1), eq(-1));
        require(cycle.cycleCount(-7), eq(-1));
        require(cycle.cycleCount(-8), eq(-2));
    }

    @Test public void testDayOfCycle() {
        title("testDayOfCycle");

        require(cycle.dayOfCycle(0), eq(2));
        require(cycle.dayOfCycle(2), eq(4));
        require(cycle.dayOfCycle(4), eq(6));
        require(cycle.dayOfCycle(5), eq(0));

        require(cycle.dayOfCycle(-1), eq(1));
        require(cycle.dayOfCycle(-2), eq(0));
        require(cycle.dayOfCycle(-3), eq(6));
    }

    @Test public void testFraction() {
        title("testFraction");

        require(cycle.fraction(0), hasString("%.2f", "0.29"));
        require(cycle.fraction(2), hasString("%.2f", "0.57"));
        require(cycle.fraction(4), hasString("%.2f", "0.86"));
        require(cycle.fraction(5), hasString("%.2f", "0.00"));

        require(cycle.fraction(-1), hasString("%.2f", "0.14"));
        require(cycle.fraction(-2), hasString("%.2f", "0.00"));
        require(cycle.fraction(-3), hasString("%.2f", "0.86"));
    }

    @Test public void testRealValue() {
        title("testRealValue");
        for (int i = -14; i <= 14; i++) {
            require(cycle.realValue(i), eq((double)cycle.dayOfCycle(i)));
        }
    }

    @Test public void testToString() {
        title("testToString");

        for (int i = -14; i <= 14; i++) {
            require(cycle.toString(i), eq(Integer.toString(cycle.dayOfCycle(i))));
        }
    }
}

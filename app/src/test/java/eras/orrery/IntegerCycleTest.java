package eras.orrery;

import eras.Ted;
import org.junit.Test;

import static eras.checker.Checker.*;

public class IntegerCycleTest extends Ted {
    // Day 0 is a Tuesday
    IntegerCycle cycle = new IntegerCycle("Test", 7, 2);

    @Test public void testBasics() {
        title("testBasics");

        require(cycle.length(), eq(7));
        require(cycle.startDay(), eq(2));
    }

    @Test public void testCycleCount() {
        title("testCycleCount");

        require(cycle.get(0).cycleCount(), eq(0));
        require(cycle.get(6).cycleCount(), eq(0));
        require(cycle.get(7).cycleCount(), eq(1));
        require(cycle.get(13).cycleCount(), eq(1));
        require(cycle.get(14).cycleCount(), eq(2));

        require(cycle.get(-1).cycleCount(), eq(-1));
        require(cycle.get(-7).cycleCount(), eq(-1));
        require(cycle.get(-8).cycleCount(), eq(-2));
    }

    @Test public void testDayOfCycle() {
        title("testDayOfCycle");

        require(cycle.get(0).dayOfCycle(), eq(2));
        require(cycle.get(2).dayOfCycle(), eq(4));
        require(cycle.get(4).dayOfCycle(), eq(6));
        require(cycle.get(5).dayOfCycle(), eq(0));

        require(cycle.get(-1).dayOfCycle(), eq(1));
        require(cycle.get(-2).dayOfCycle(), eq(0));
        require(cycle.get(-3).dayOfCycle(), eq(6));
    }

    @Test public void testFraction() {
        title("testFraction");

        require(cycle.get(0).fraction(), hasString("%.2f", "0.29"));
        require(cycle.get(2).fraction(), hasString("%.2f", "0.57"));
        require(cycle.get(4).fraction(), hasString("%.2f", "0.86"));
        require(cycle.get(5).fraction(), hasString("%.2f", "0.00"));

        require(cycle.get(-1).fraction(), hasString("%.2f", "0.14"));
        require(cycle.get(-2).fraction(), hasString("%.2f", "0.00"));
        require(cycle.get(-3).fraction(), hasString("%.2f", "0.86"));
    }

    @Test public void testRealValue() {
        title("testRealValue");
        for (int i = -14; i <= 14; i++) {
            CycleValue cv = cycle.get(i);
            require(cv.realValue(), eq((double)cv.dayOfCycle()));
        }
    }

    @Test public void testToString() {
        title("testToString");

        for (int i = -14; i <= 14; i++) {
            CycleValue cv = cycle.get(i);
            require(cv.toString(), eq(Integer.toString(cv.dayOfCycle())));
        }
    }
}

package eras.orrery;

import eras.Ted;
import org.junit.Test;

import static eras.checker.Checker.*;

public class RealCycleTest extends Ted {
    RealCycle cycle = new RealCycle(28.25, 7.3);

    @Test public void testBasics() {
        title("testBasics");

        require(cycle.length(), eq(28.25));
        require(cycle.startValue(), eq(7.3));
    }

    @Test public void testOutput() {
        CycleFunctions.dumpRealCycle(cycle, -40, 80);
    }

    @Test public void testCycleCount() {
        title("testCycleCount");

        require(cycle.cycleCount(0), eq(0));
        require(cycle.cycleCount(29), eq(1));
        require(cycle.cycleCount(-1), eq(-1));
        require(cycle.cycleCount(-30), eq(-2));
    }

    @Test public void testRealValue() {
        title("testRealValue");

        require(cycle.realValue(0), hasString("%.2f", "7.30"));
        require(cycle.realValue(10), hasString("%.2f", "17.30"));
        require(cycle.realValue(22), hasString("%.2f", "1.05"));
    }

    @Test public void testFraction() {
        title("testFraction");
        require(cycle.fraction(0), hasString("%.2f", "0.26"));
        require(cycle.fraction(10), hasString("%.2f", "0.61"));
        require(cycle.fraction(22), hasString("%.2f", "0.04"));
    }

    @Test public void testDayOfCycle() {
        title("testDayOfCycle");
        for (int i = -30; i <= 30; i++) {
            require(cycle.dayOfCycle(i), eq((int)Math.floor(cycle.realValue(i))));
        }
    }

    @Test public void testToString() {
        title("testToString");

        for (int i = -14; i <= 14; i++) {
            require(cycle.toString(i), eq(String.format("%.2f", cycle.realValue(i))));
        }
    }
}

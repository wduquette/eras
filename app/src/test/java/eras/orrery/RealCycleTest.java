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

        require(cycle.get(0).cycleCount(), eq(0));
        require(cycle.get(29).cycleCount(), eq(1));
        require(cycle.get(-1).cycleCount(), eq(-1));
        require(cycle.get(-30).cycleCount(), eq(-2));
    }

    @Test public void testRealValue() {
        title("testRealValue");

        require(cycle.get(0).realValue(), hasString("%.2f", "7.30"));
        require(cycle.get(10).realValue(), hasString("%.2f", "17.30"));
        require(cycle.get(22).realValue(), hasString("%.2f", "1.05"));
    }

    @Test public void testFraction() {
        title("testFraction");
        require(cycle.get(0).fraction(), hasString("%.2f", "0.26"));
        require(cycle.get(10).fraction(), hasString("%.2f", "0.61"));
        require(cycle.get(22).fraction(), hasString("%.2f", "0.04"));
    }

    @Test public void testDayOfCycle() {
        title("testDayOfCycle");
        for (int i = -30; i <= 30; i++) {
            CycleValue cv = cycle.get(i);
            require(cv.dayOfCycle(), eq((int)Math.floor(cv.realValue())));
        }
    }

    @Test public void testToString() {
        title("testToString");

        for (int i = -14; i <= 14; i++) {
            CycleValue cv = cycle.get(i);
            require(cv.toString(), eq(String.format("%.2f", cv.realValue())));
        }
    }
}

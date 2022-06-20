package eras.orrery;

import eras.Ted;
import org.junit.Test;

import static eras.checker.Checker.*;

public class RealCycleTest extends Ted {
    RealCycle cycle = new RealCycle(28.25, 7.3, 0);

    @Test public void testBasics() {
        title("testBasics");

        require(cycle.length(), eq(28.25));
        require(cycle.startValue(), eq(7.3));
        require(cycle.day(), eq(0));
    }

    @Test public void testOutput() {
        cycle = new RealCycle(28.25, 7.3, 0);
        CycleFunctions.dumpRealCycle(cycle, -40, 80);
    }

    @Test public void testSetDay() {
        title("testSetDay");

        Cycle cycle2 = cycle.setDay(3);
        require(cycle2.day(), eq(3));
    }

    @Test public void testCycleCount() {
        title("testCycleCount");
        Cycle that = cycle;

        require(that.cycleCount(), eq(0));
        that = that.setDay(29);
        require(that.cycleCount(), eq(1));
        that = that.setDay(-1);
        require(that.cycleCount(), eq(-1));
        that = that.setDay(-30);
        require(that.cycleCount(), eq(-2));
    }

    @Test public void testRealValue() {
        title("testRealValue");
        Cycle that = cycle;

        require(that.realValue(), hasString("%.2f", "7.30"));
        that = that.setDay(10);
        require(that.realValue(), hasString("%.2f", "17.30"));
        that = that.setDay(22);
        require(that.realValue(), hasString("%.2f", "1.05"));
    }

    @Test public void testFraction() {
        title("testFraction");
        Cycle that = cycle;

        require(that.fraction(), hasString("%.2f", "0.26"));
        that = that.setDay(10);
        require(that.fraction(), hasString("%.2f", "0.61"));
        that = that.setDay(22);
        require(that.fraction(), hasString("%.2f", "0.04"));
    }

    @Test public void testDayOfCycle() {
        title("testDayOfCycle");
        Cycle that = cycle;

        for (int i = -30; i <= 30; i++) {
            that = that.setDay(i);
            require(that.dayOfCycle(), eq((int)Math.floor(that.realValue())));
        }
    }

    @Test public void testToString() {
        title("testToString");
        Cycle that = cycle;

        for (int i = -14; i <= 14; i++) {
            that = that.setDay(i);
            require(that.toString(), eq(String.format("%.2f", that.realValue())));
        }
    }

}

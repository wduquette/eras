package eras.orrery;

import eras.Ted;
import org.junit.Test;

import static eras.checker.Checker.*;

public class RealCycleTest extends Ted {
    RealCycle cycle = new RealCycle(28.25, 7.2, 0);

    @Test public void testBasics() {
        title("testBasics");

        require(cycle.length(), eq(28.25));
        require(cycle.startDay(), eq(7.2));
        require(cycle.day(), eq(0));
    }

    @Test public void testSetDay() {
        title("testSetDay");

        Cycle cycle2 = cycle.setDay(3);
        require(cycle2.day(), eq(3));
    }

    @Test public void testCurrentCycle() {
        title("testCurrentCycle");
        Cycle that = cycle;

        require(that.currentCycle(), eq(0));
        that = that.setDay(29);
        require(that.currentCycle(), eq(1));
        that = that.setDay(-1);
        require(that.currentCycle(), eq(-1));
        that = that.setDay(-30);
        require(that.currentCycle(), eq(-2));
    }

    @Test public void testRealDay() {
        title("testRealDay");
        Cycle that = cycle;

        for (int i = 0; i < 100; i++) {
            that = that.setDay(i);
            System.out.printf("%02d: %02d %4.2f\n",
                that.day(), that.currentDay(), that.realDay());
        }

        require(that.realDay(), hasString("%.2f", "7.20"));
        that = that.setDay(10);
        require(that.realDay(), hasString("%.2f", "17.20"));
        that = that.setDay(22);
        require(that.realDay(), hasString("%.2f", "0.95"));
    }

    @Test public void testCurrentFraction() {
        title("testCurrentDay");
        Cycle that = cycle;

        require(that.currentFraction(), hasString("%.2f", "0.29"));
        that = that.setDay(2);
        require(that.currentFraction(), hasString("%.2f", "0.57"));
        that = that.setDay(4);
        require(that.currentFraction(), hasString("%.2f", "0.86"));
        that = that.setDay(5);
        require(that.currentFraction(), hasString("%.2f", "0.00"));

        that = that.setDay(-1);
        require(that.currentFraction(), hasString("%.2f", "0.14"));
        that = that.setDay(-2);
        require(that.currentFraction(), hasString("%.2f", "0.00"));
        that = that.setDay(-3);
        require(that.currentFraction(), hasString("%.2f", "0.86"));
    }

    @Test public void testCurrentDay() {
        title("testCurrentDay");
//        Cycle that = cycle;
//
//        for (int i = -14; i <= 14; i++) {
//            that = that.setDay(i);
//            require(that.realDay(), eq((double)that.currentDay()));
//        }
    }

    @Test public void testToString() {
        title("testToString");
        Cycle that = cycle;

        for (int i = -14; i <= 14; i++) {
            that = that.setDay(i);
            require(that.toString(), eq(Integer.toString(that.currentDay())));
        }
    }
}

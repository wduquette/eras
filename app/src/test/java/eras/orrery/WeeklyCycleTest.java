package eras.orrery;

import eras.Ted;
import org.junit.Test;

import java.util.List;

import static eras.checker.Checker.*;

public class WeeklyCycleTest extends Ted {
    // Day 0 is a Tuesday
    List<String> dayNames = List.of("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat");
    WeeklyCycle cycle = new WeeklyCycle(dayNames, 2, 0);

    @Test public void testBasics() {
        title("testBasics");

        require(cycle.length(), eq(7));
        require(cycle.startDay(), eq(2));
        require(cycle.day(), eq(0));

    }

    @Test public void testToDay() {
        title("testToDay");

        Cycle cycle2 = cycle.setDay(3);
        require(cycle2.day(), eq(3));
    }

    @Test public void testCurrentCycle() {
        title("testCurrentCycle");
        Cycle that = cycle;

        require(that.cycleCount(), eq(0));
        that = that.setDay(6);
        require(that.cycleCount(), eq(0));
        that = that.setDay(7);
        require(that.cycleCount(), eq(1));
        that = that.setDay(13);
        require(that.cycleCount(), eq(1));
        that = that.setDay(14);
        require(that.cycleCount(), eq(2));

        that = that.setDay(-1);
        require(that.cycleCount(), eq(-1));
        that = that.setDay(-7);
        require(that.cycleCount(), eq(-1));
        that = that.setDay(-8);
        require(that.cycleCount(), eq(-2));
    }

    @Test public void testCurrentDay() {
        title("testCurrentDay");
        Cycle that = cycle;

        require(that.dayOfCycle(), eq(2));
        that = that.setDay(2);
        require(that.dayOfCycle(), eq(4));
        that = that.setDay(4);
        require(that.dayOfCycle(), eq(6));
        that = that.setDay(5);
        require(that.dayOfCycle(), eq(0));

        that = that.setDay(-1);
        require(that.dayOfCycle(), eq(1));
        that = that.setDay(-2);
        require(that.dayOfCycle(), eq(0));
        that = that.setDay(-3);
        require(that.dayOfCycle(), eq(6));
    }

    @Test public void testCurrentFraction() {
        title("testCurrentDay");
        Cycle that = cycle;

        require(that.fraction(), hasString("%.2f", "0.29"));
        that = that.setDay(2);
        require(that.fraction(), hasString("%.2f", "0.57"));
        that = that.setDay(4);
        require(that.fraction(), hasString("%.2f", "0.86"));
        that = that.setDay(5);
        require(that.fraction(), hasString("%.2f", "0.00"));

        that = that.setDay(-1);
        require(that.fraction(), hasString("%.2f", "0.14"));
        that = that.setDay(-2);
        require(that.fraction(), hasString("%.2f", "0.00"));
        that = that.setDay(-3);
        require(that.fraction(), hasString("%.2f", "0.86"));
    }

    @Test public void testRealDay() {
        title("testRealDay");
        Cycle that = cycle;

        for (int i = -14; i <= 14; i++) {
            that = that.setDay(i);
            require(that.realValue(), eq((double)that.dayOfCycle()));
        }
    }

    @Test public void testToString() {
        title("testToString");
        Cycle that = cycle;

        for (int i = -14; i <= 14; i++) {
            that = that.setDay(i);
            require(that.toString(), eq(dayNames.get(that.dayOfCycle())));
        }
    }
}
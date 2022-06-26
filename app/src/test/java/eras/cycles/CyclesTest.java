package eras.cycles;

import eras.Ted;
import org.junit.Test;

import java.util.List;
import static eras.checker.Checker.*;
import static eras.cycles.Cycles.*;

public class CyclesTest extends Ted {
    // Day 0 is a Tuesday
    List<String> dayNames = List.of("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat");
    Cycle.Weekly weeklyCycle = new Cycle.Weekly("Week", dayNames, 2);
    Cycle.Lunar lunarCycle = new Cycle.Lunar("Lunar", 28.25, 7.3);
    CycleValue value;
    CycleValue.LunarValue lunarValue;
    CycleValue.WeeklyValue weeklyValue;

    @Test public void testIntCycleCount() {
        require(intCycleCount(0, 7), eq(0));
        require(intCycleCount(6, 7), eq(0));
        require(intCycleCount(7, 7), eq(1));
        require(intCycleCount(13, 7), eq(1));
        require(intCycleCount(14, 7), eq(2));

        require(intCycleCount(-1, 7), eq(-1));
        require(intCycleCount(-7, 7), eq(-1));
        require(intCycleCount(-8, 7), eq(-2));
    }

    @Test public void testRealCycleCount() {
        require(realCycleCount(0, 28.25), eq(0));
        require(realCycleCount(29, 28.25), eq(1));
        require(realCycleCount(-1, 28.25), eq(-1));
        require(realCycleCount(-30, 28.25), eq(-2));
    }

    @Test public void testDayOfCycle_basic() {
        require(dayOfCycle(-3, 7, 2), eq(6));
        require(dayOfCycle(-2, 7, 2), eq(0));
        require(dayOfCycle(-1, 7, 2), eq(1));
        require(dayOfCycle( 0, 7, 2), eq(2));
        require(dayOfCycle( 4, 7, 2), eq(6));
        require(dayOfCycle( 5, 7, 2), eq(0));
    }

    @Test public void testPosition_basic() {
        require(position( 0, 28.25, 7.3), hasString("%.2f", "7.30"));
        require(position(10, 28.25, 7.3), hasString("%.2f", "17.30"));
        require(position(22, 28.25, 7.3), hasString("%.2f", "1.05"));
    }

    @Test public void testCompute_lunar() {
        value = Cycles.compute(lunarCycle, 10);
        require(value, isa(CycleValue.LunarValue.class));

        lunarValue = (CycleValue.LunarValue)value;
        require(lunarValue.cycle(), isa(Cycle.Lunar.class));
        require(lunarValue.day(), eq(10));
    }

    @Test public void testCompute_weekly() {
        value = Cycles.compute(weeklyCycle, 10);
        require(value, isa(CycleValue.WeeklyValue.class));

        weeklyValue = (CycleValue.WeeklyValue)value;
        require(weeklyValue.cycle(), isa(Cycle.Weekly.class));
        require(weeklyValue.day(), eq(10));
    }

    @Test public void testCycleCount_lunar() {
        for (int d = -30; d <= 30; d++) {
            require(cycleCount(lunarCycle, d), eq(realCycleCount(d, 28.25)));
        }
    }

    @Test public void testCycleCount_weekly() {
        for (int d = -30; d <= 30; d++) {
            require(cycleCount(weeklyCycle, d), eq(intCycleCount(d, 7)));
        }
    }

    @Test public void testDayOfCycle_lunar() {
        for (int d = -30; d <= 30; d++) {
            require(dayOfCycle(lunarCycle, d),
                eq((int)Math.floor(position(lunarCycle, d))));
        }
    }

    @Test public void testDayOfCycle_weekly() {
        for (int d = -30; d <= 30; d++) {
            require(dayOfCycle(weeklyCycle, d), eq(dayOfCycle(d, 7, 2)));
        }
    }

    @Test public void testPosition_lunar() {
        for (int d = -30; d <= 30; d++) {
            require(position(lunarCycle, d), eq(position(d, 28.25, 7.3)));
        }
    }

    @Test public void testPosition_weekly() {
        for (int d = -30; d <= 30; d++) {
            require(position(weeklyCycle, d), eq((double)dayOfCycle(d, 7, 2)));
        }
    }

    @Test public void testFraction_lunar() {
        for (int d = -30; d <= 30; d++) {
            require(fraction(lunarCycle, d), eq(position(lunarCycle, d)/28.25));
        }
    }

    @Test public void testFraction_weekly() {
        for (int d = -30; d <= 30; d++) {
            require(fraction(weeklyCycle, d), eq(position(weeklyCycle, d)/7));
        }
    }

    @Test public void testLunarShape() {

    }

    @Test public void testLunarPhase() {

    }

    @Test public void testDayName() {
        for (int d = -30; d <= 30; d++) {
            require(dayName(weeklyCycle, d),
                eq(dayNames.get(dayOfCycle(weeklyCycle, d))));
        }
    }

    @Test public void testDayOfWeek() {
        for (int d = -30; d <= 30; d++) {
            require(dayOfWeek(weeklyCycle, d),
                eq(dayOfCycle(weeklyCycle, d) + 1));
        }
    }
}

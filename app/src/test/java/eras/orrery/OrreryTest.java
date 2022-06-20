package eras.orrery;

import eras.Ted;
import org.junit.Test;

import java.util.List;

public class OrreryTest extends Ted {
    // Set up an orrery with a weekly cycle and a lunar cycle

    // Day 0 is a Tuesday
    List<String> dayNames = List.of("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat");
    WeeklyCycle weekly = new WeeklyCycle(dayNames, 2);
    LunarCycle monthly = new LunarCycle(27.322, 7.2);

    @Test public void testOutput() {
        title("testOutput");

        println("Day      Moon");
        println("-------- --------------------");
        for (int day = 0; day <= 60; day++) {
            WeeklyCycleValue week = weekly.get(day);
            LunarCycleValue lunar = monthly.get(day);
            printf("[%2d] %s %-8s %-6s %.2f\n",
                day, week.dayName(), lunar.shape(), lunar.phase(),
                lunar.fraction());
        }

    }
}

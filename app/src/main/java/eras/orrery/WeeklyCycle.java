package eras.orrery;

import java.util.List;

/**
 * A cycle that models days of the week.  The starting
 * day determines the currentCycle and currentDay as of day=0.
 *
 * @param dayNames The names of the days in the week
 * @param startDay The starting day
 * @param day      The current day
 */
public record WeeklyCycle(List<String> dayNames, int startDay, int day)
    implements Cycle
{
    //-------------------------------------------------------------------------
    // Cycle API

    @Override
    public Cycle setDay(int newDay) {
        return new WeeklyCycle(dayNames, startDay, newDay);
    }

    @Override
    public int cycleCount() {
        return CycleFunctions.cycleCount(day, length());
    }

    @Override
    public int dayOfCycle() {
        return CycleFunctions.dayOfCycle(day, length(), startDay);
    }

    @Override
    public double realValue() {
        return dayOfCycle();
    }

    @Override
    public double fraction() {
        return realValue()/length();
    }

    //-------------------------------------------------------------------------
    // WeeklyCycle API

    /**
     * Returns the length of the cycle in days.
     * @return The length
     */
    public int length() {
        return dayNames.size();
    }

    /**
     * Gets the weekday name for the dayOfCycle().
     * @return The name
     */
    public String dayName() {
        return dayNames.get(dayOfCycle());
    }

    /**
     * Gets the day-of-week as an integer 1 to the number of days in the week.
     * @return The number
     */
    public int dayOfWeek() {
        return dayOfCycle() + 1;
    }

    /**
     * Returns the dayName() string.
     * @return The day name string.
     */
    @Override
    public String toString() {
        return dayName();
    }
}

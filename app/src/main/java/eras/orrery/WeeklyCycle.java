package eras.orrery;

import eras.util.Functions;

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
    public int length() {
        return dayNames.size();
    }

    public int currentCycle() {
        return CycleFunctions.currentCycle(day, length());
    }

    public int currentDay() {
        return CycleFunctions.currentDay(day, length(), startDay);
    }

    public double realDay() {
        return currentDay();
    }

    public double currentFraction() {
        return realDay()/length();
    }

    public Cycle setDay(int newDay) {
        return new WeeklyCycle(dayNames, startDay, newDay);
    }

    /**
     * Gets the weekday name for the currentDay().
     * @return The name
     */
    public String dayName() {
        return dayNames.get(currentDay());
    }

    /**
     * Gets the day-of-week as an integer 1 to the number of days in the week.
     * @return The number
     */
    public int dayOfWeek() {
        return currentDay() + 1;
    }

    /**
     * Gets the week day name for the currentDay().
     * @return The day name string.
     */
    @Override
    public String toString() {
        return dayName();
    }
}

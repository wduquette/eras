package eras.orrery;

import eras.util.Functions;

/**
 * A cycle that models integer cycles, e.g., days of the week.  The starting
 * day determines the currentCycle and currentDay as of day=0.
 *
 * @param length   The length of the cycle in days
 * @param startDay The starting day
 * @param day      The current day
 */
public record IntegerCycle(int length, int startDay, int day)
    implements Cycle
{
    public int currentCycle() {
        if (day >= 0) {
            return day / length;
        } else {
            return -1 + ((day + 1) / length);
        }
    }

    public int currentDay() {
        return Functions.mod((day + startDay), length);
    }

    public double realDay() {
        return currentDay();
    }

    public double currentFraction() {
        return realDay()/length;
    }

    public Cycle setDay(int newDay) {
        return new IntegerCycle(length, startDay, newDay);
    }

    @Override
    public String toString() {
        return Integer.toString(currentDay());
    }
}

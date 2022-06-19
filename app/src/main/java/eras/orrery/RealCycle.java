package eras.orrery;

import eras.util.Functions;

/**
 * A cycle that models natural cycles: the solar year, the lunar month.  The
 * length will seldom be an integer number of days, and the startDay will
 * often not be zero.
 *
 * @param length   The length of the cycle in days
 * @param startDay The position within the cycle on day 0
 * @param day      The current day
 */
public record RealCycle(double length, double startDay, int day)
    implements Cycle
{
    @Override
    public int currentCycle() {
        return (int)Math.floor((day + startDay) / length);
    }

    @Override
    public double realDay() {
        return Functions.fmod((day + startDay), length);
    }

    @Override
    public int currentDay() {
        return (int)realDay();
    }

    @Override
    public double currentFraction() {
        return realDay()/length();
    }

    @Override
    public Cycle setDay(int newDay) {
        return new RealCycle(length, startDay, newDay);
    }

    @Override
    public String toString() {
        return String.format("%.2f", realDay());
    }
}

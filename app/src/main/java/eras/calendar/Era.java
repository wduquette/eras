package eras.calendar;

import eras.orrery.Cycle;

import java.util.List;

/**
 * This class defines a yearly calendar in terms of its element.
 * of days in the year can depend on the numerical year.
 *
 * @param calendar
 * @param startYear
 * @param startDayOfYear
 * @param day
 */
public record Era(
    Calendar calendar,
    int startYear,
    int startDayOfYear,
    int day
)
    implements Cycle
{
    @Override
    public Cycle setDay(int newDay) {
        // TODO: This is complicated, because it requires simulation in the
        // face of leap days.
        return null;
    }

    @Override
    public int cycleCount() {
        return 0;
    }

    @Override
    public int dayOfCycle() {
        return 0;
    }

    @Override
    public double realValue() {
        return 0;
    }

    @Override
    public double fraction() {
        return 0;
    }
}

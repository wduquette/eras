package eras.orrery;

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
    public Cycle setDay(int newDay) {
        return new RealCycle(length, startDay, newDay);
    }

    @Override
    public int cycleCount() {
        return CycleFunctions.cycleCount(day, length);
    }

    @Override
    public double realValue() {
        return CycleFunctions.realValue(day, length, startDay);
    }

    @Override
    public int dayOfCycle() {
        return (int)Math.floor(realValue());
    }

    @Override
    public double fraction() {
        return realValue()/length();
    }

    @Override
    public String toString() {
        return String.format("%.2f", realValue());
    }
}

package eras.orrery;

/**
 * A cycle that models natural cycles: the solar year, the lunar month.  The
 * length will seldom be an integer number of days, and the startDay will
 * often not be zero.
 *
 * @param length   The length of the cycle in days
 * @param startValue The cycle's value on day()=0
 */
public record RealCycle(double length, double startValue) implements Cycle {
    @Override
    public int cycleCount(int day) {
        return CycleFunctions.cycleCount(day, length);
    }

    @Override
    public double realValue(int day) {
        return CycleFunctions.realValue(day, length, startValue);
    }

    @Override
    public int dayOfCycle(int day) {
        return (int)Math.floor(realValue(day));
    }

    @Override
    public double fraction(int day) {
        return realValue(day)/length();
    }

    @Override
    public String toString(int day) {
        return String.format("%.2f", realValue(day));
    }
}

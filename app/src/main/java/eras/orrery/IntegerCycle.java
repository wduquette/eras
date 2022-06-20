package eras.orrery;

/**
 * A cycle that models integer cycles, e.g., days of the week.  The starting
 * day determines the currentCycle and currentDay as of day=0.
 *
 * @param length   The length of the cycle in days
 * @param startDay The starting day
 */
public record IntegerCycle(int length, int startDay) implements Cycle {
    @Override
    public int cycleCount(int day) {
        return CycleFunctions.cycleCount(day, length);
    }

    @Override
    public int dayOfCycle(int day) {
        return CycleFunctions.dayOfCycle(day, length, startDay);
    }

    @Override
    public double realValue(int day) {
        return dayOfCycle(day);
    }

    @Override
    public double fraction(int day) {
        return realValue(day)/length;
    }

    @Override
    public String toString(int day) {
        return Integer.toString(dayOfCycle(day));
    }
}

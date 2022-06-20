package eras.orrery;

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
    @Override
    public Cycle setDay(int newDay) {
        return new IntegerCycle(length, startDay, newDay);
    }

    @Override
    public int cycleCount() {
        return CycleFunctions.cycleCount(day, length);
    }

    @Override
    public int dayOfCycle() {
        return CycleFunctions.dayOfCycle(day, length, startDay);
    }

    @Override
    public double realValue() {
        return dayOfCycle();
    }

    @Override
    public double fraction() {
        return realValue()/length;
    }

    @Override
    public String toString() {
        return Integer.toString(dayOfCycle());
    }
}

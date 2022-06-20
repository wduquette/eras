package eras.orrery;

/**
 * The value of a RealCycle.
 *
 * @param day   Days since the epoch
 * @param cycle The underlying cycle
 */
public record RealCycleValue(int day, RealCycle cycle) implements CycleValue {
    @Override
    public int cycleCount() {
        return CycleFunctions.cycleCount(day, cycle.length());
    }

    @Override
    public double realValue() {
        return CycleFunctions.realValue(day, cycle.length(), cycle.startValue());
    }

    @Override
    public int dayOfCycle() {
        return (int)Math.floor(realValue());
    }

    @Override
    public double fraction() {
        return realValue()/cycle.length();
    }

    @Override
    public String toString() {
        return String.format("%.2f", realValue());
    }
}

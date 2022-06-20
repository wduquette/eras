package eras.orrery;

/**
 * A lunar cycle is the cycle of a moon.  It maps the fraction() of the cycle
 * to the phase of the moon: new=0.0, waxing=0.25, full=0.5, waning=0.75.
 *
 * @param length     The length of the cycle in days
 * @param startValue The cycle's realValue() on day() = 0
 * @param day        The current day
 */
public record LunarCycle(double length, double startValue, int day)
    implements Cycle
{
    //-------------------------------------------------------------------------
    // RealCycle API

    @Override
    public Cycle setDay(int newDay) {
        return new LunarCycle(length, startValue, newDay);
    }

    @Override
    public int cycleCount() {
        return CycleFunctions.cycleCount(day, length);
    }

    @Override
    public double realValue() {
        return CycleFunctions.realValue(day, length, startValue);
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

    //-------------------------------------------------------------------------
    // LunarCycle API

    /**
     * The appearance of the moon.
     */
    public enum Shape {
        NEW,
        CRESCENT,
        HALF,
        GIBBOUS,
        FULL
    }

    /**
     * The phase of the moon.
     */
    public enum Phase {
        NEW,
        WAXING,
        FULL,
        WANING
    }

    /**
     * Returns the shape of the moon.
     * @return The shape
     */
    public Shape shape() {
        double frac = fraction();

        // 0.96 to 0.40    NEW
        //                 CRESCENT
        // 0.21 to 0.29    HALF
        //                 GIBBOUS
        // 0.46 to 0.54    FULL
        //                 GIBBOUS
        // 0.71 to 0.79    HALF
        //                 CRESCENT
        // 0.96 to 0.40    NEW

        if (frac <= 0.04) {
            return Shape.NEW;
        } else if (frac < 0.21) {
            return Shape.CRESCENT;
        } else if (frac <= 0.29) {
            return Shape.HALF;
        } else if (frac < 0.46) {
            return Shape.GIBBOUS;
        } else if (frac <= 0.54) {
            return Shape.FULL;
        } else if (frac < 0.71) {
            return Shape.GIBBOUS;
        } else if (frac <= 0.79) {
            return Shape.HALF;
        } else if (frac < 0.96) {
            return Shape.CRESCENT;
        } else {
            return Shape.NEW;
        }
    }

    /**
     * Returns the phase of the moon.
     * @return The phase
     */
    public Phase phase() {
        double frac = fraction();

        // 0.96 to 0.04 - NEW
        //                WAXING
        // 0.46 to 0.54 - FULL
        //                WANING

        if (frac <= 0.04) {
            return Phase.NEW;
        } else if (frac < 0.46) {
            return Phase.WAXING;
        } else if (frac <= 0.54) {
            return Phase.FULL;
        } else if (frac < 0.96) {
            return Phase.WANING;
        } else {
            return Phase.NEW;
        }
    }
}

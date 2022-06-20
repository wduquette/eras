package eras.orrery;

/**
 * A lunar cycle is the cycle of a moon.  It maps the fraction() of the cycle
 * to the phase of the moon: new=0.0, waxing=0.25, full=0.5, waning=0.75.
 *
 * @param length     The length of the cycle in days
 * @param startValue The cycle's realValue() on day() = 0
 */
public record LunarCycle(double length, double startValue)
    implements Cycle
{
    //-------------------------------------------------------------------------
    // RealCycle API

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
     * @param day Days since the epoch
     * @return The shape
     */
    public Shape shape(int day) {
        double frac = fraction(day);

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
     * @param day Days since the epoch
     * @return The phase
     */
    public Phase phase(int day) {
        double frac = fraction(day);

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

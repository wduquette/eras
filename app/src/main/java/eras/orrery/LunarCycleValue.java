package eras.orrery;

import eras.orrery.LunarCycle.*;

/**
 * The value of a LunarCycle. It maps the fraction() of the cycle
 * to the phase of the moon: new=0.0, waxing=0.25, full=0.5, waning=0.75.
 *
 * @param day   Days since the epoch
 * @param cycle The underlying cycle.
 */
public record LunarCycleValue(int day, LunarCycle cycle)
    implements CycleValue
{
    //-------------------------------------------------------------------------
    // RealCycle API

    @Override
    public int cycleCount() {
        return CycleFunctions.cycleCount(day, cycle.length());
    }

    @Override
    public double realValue() {
        return CycleFunctions.realValue(day, cycle.length(),
            cycle.startValue());
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

    //-------------------------------------------------------------------------
    // LunarCycle API


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

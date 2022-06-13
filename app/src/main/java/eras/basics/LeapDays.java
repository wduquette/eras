package eras.basics;

import static eras.util.Functions.mod;

/**
 * The interface LeapDays defines how leap days are computed for a year element.
 */
public sealed interface LeapDays {
    public static final LeapDays NONE = new LeapDays.None();
    public static final LeapDays GREGORIAN = new LeapDays.Gregorian();
    public static final LeapDays JULIAN = new LeapDays.EveryN(4);

    //-------------------------------------------------------------------------
    // Rule API

    /**
     * Computes the number of leap days given the current year.
     * @param year The year
     * @return The number of leap days
     */
    int forYear(int year);

    //-------------------------------------------------------------------------
    // Rules

    record None() implements LeapDays {
        public int forYear(int year) { return 0; }
    }

    /**
     * Add a leap day on years divisible by 4, except for centuries not
     * divisible by 400.
     */
    record Gregorian() implements LeapDays {
        public int forYear(int year) {
            // TODO: Doesn't handle years < 1!
            if (mod(year, 400) == 0) {
                return 1;
            } else if (mod(year, 100) == 0) {
                return 0;
            } else if (mod(year, 4) == 0) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    /**
     * Add a leap day on years evenly divisible by n.
     * @param n The n
     */
    record EveryN(int n) implements LeapDays {
        public int forYear(int year) {
            // TODO: Doesn't handle years < 1!
            if (mod(year, n) == 0) {
                return 1;
            } else {
                return 0;
            }
        }
    }
}

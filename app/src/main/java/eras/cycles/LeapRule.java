package eras.cycles;

import static eras.util.Functions.mod;

/**
 * The interface LeapDays defines how leap days are computed for a year element.
 */
public sealed interface LeapRule {
    LeapRule NONE = new LeapRule.None();
    LeapRule GREGORIAN = new LeapRule.Gregorian();
    LeapRule JULIAN = new LeapRule.EveryN(4);

    //-------------------------------------------------------------------------
    // Rules

    record None() implements LeapRule { }

    /**
     * Add a leap day on years divisible by 4, except for centuries not
     * divisible by 400.
     */
    record Gregorian() implements LeapRule { }

    /**
     * Add a leap day on years evenly divisible by n.
     * @param n The n
     */
    record EveryN(int n) implements LeapRule { }
}

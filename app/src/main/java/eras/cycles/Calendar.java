package eras.cycles;

import eras.calendar.YearElement;

import static eras.util.Functions.mod;

public class Calendar {
    private Calendar() {} // Not instantiable

    /**
     * Computes the length of a year element in days, given the current year
     * and the element's associated LeapRule.
     * @param element The element
     * @param year The year
     * @return The length of the element in days
     */
    public static int lengthOfYearElementInDays(YearElement element, int year) {
        return switch (element) {
            case eras.calendar.YearElement.Month m ->
                m.days() + m.leapDays().forYear(year);
            case YearElement.Epagomenal m ->
                m.days() + m.leapDays().forYear(year);
        };
    }

    /**
     * Computes the length of a calendar year in days, given the current year
     * and the calendar schema's associated LeapRules.
     * @param calendar The calendar schema
     * @param year The numerical year
     * @return The number of days
     */
    public static int lengthOfYearInDays(CalendarSchema calendar, int year) {
        return calendar.elements().stream()
            .mapToInt(e -> lengthOfYearElementInDays(e, year))
            .sum();
    }

    /**
     * Computes the number of leap days in the year, given the rule.
     * @param rule The rule
     * @param year The number of the year
     * @return The number of leap days.
     */
    public static int numberOfLeapDays(LeapRule rule, int year) {
        return switch (rule) {
            case LeapRule.None r -> 0;
            case LeapRule.Gregorian r -> leapDaysGregorian(year);
            case LeapRule.EveryN r -> leapDayEveryNYears(r.n(), year);
        };
    }

    // Computes the number of leap days in a year according to the
    // Gregorian rule.
    private static int leapDaysGregorian(int year) {
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

    // Computes the number of leap days according to the rule 1 leap day
    // in years that are evenly divisible by N, and 0 otherwise.
    private static int leapDayEveryNYears(int everyN, int year) {
        // TODO: Doesn't handle years < 1!
        if (mod(year, everyN) == 0) {
            return 1;
        } else {
            return 0;
        }
    }
}

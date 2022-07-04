package eras.calendar;


import static eras.util.Functions.mod;

public class Calendars {
    private Calendars() {} // Not instantiable

    /**
     * Converts a Date in the given CalendarSchema to a JulianDay in the
     * same schema
     * @param schema The calendar schema
     * @param date The input date
     * @return The Julian Day
     */
    public static JulianDay date2julian(CalendarSchema schema, Date date) {
        int year = date.year();
        int dayOfYear = date.dayOfElement();
        for (int i = 1; i < schema.size(); i++) {
            dayOfYear += lengthOfYearElementInDays(schema.element(i), year);
        }

        return JulianDay.of(year, dayOfYear);
    }

    /**
     * Converts a JulianDay in the given CalendarSchema to a Date in the
     * same schema
     * @param schema The calendar schema
     * @param julian The input day
     * @return The date
     */
    public static Date julian2date(CalendarSchema schema, JulianDay julian) {
        int year = julian.year();
        int element = 1;
        int daysLeft = julian.dayOfYear();

        while (daysLeft > lengthOfYearElementInDays(schema.element(element), year)) {
            daysLeft -= lengthOfYearElementInDays(schema.element(element), year);
            element += 1;
        }

        return Date.of(year, element, daysLeft);
    }

    /**
     * Computes the length of a year element in days, given the current year
     * and the element's associated LeapRule.
     * @param element The element
     * @param year The year
     * @return The length of the element in days
     */
    public static int lengthOfYearElementInDays(YearElement element, int year) {
        return switch (element) {
            case YearElement.Month m ->
                m.days() + numberOfLeapDays(m.leapRule(), year);
            case YearElement.Epagomenal m ->
                m.days() + numberOfLeapDays(m.leapRule(), year);
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
     * The first year of era XY is 1; the previous year is 1 BXY.  For
     * purposes of this function, the years BXY are coded 0, -1, -2, etc.
     * @param rule The rule
     * @param year The number of the year, not zero.
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
        if (mod(year, everyN) == 0) {
            return 1;
        } else {
            return 0;
        }
    }
}

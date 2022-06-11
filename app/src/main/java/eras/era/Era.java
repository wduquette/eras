package eras.era;

/**
 * An Era represents a calendar scheme based at some reference date.  It has
 * a cycle of week days and a cycle of months, allowing it to compute the
 * year, month of year, day of month, and day of week for any day expressed
 * as a count of days from the reference date.  The reference date is
 * New Year's Day of some year.
 */
public class Era {
    private String symbol = "AD";
    private Months months = Months.ENGLISH;
    private WeekDays weekDays = WeekDays.ENGLISH;
    // The reference date; also, new year's day.
    private ReferenceDate referenceDate = new ReferenceDate(2022, 1, 1, 7);

    public Era() {
        // Nothing to do yet
    }

    public static int toDay(String dateString) {
        return 0;
    }

    public static String toDateString(int day) {
        return null;
    }
}

package eras.calendar;

import java.util.List;

/**
 * This class defines a yearly calendar in terms of its element.  The number of
 * days in the year can depend on the numerical year.
 *
 * @param elements The elements.
 */
public record Calendar(List<YearElement> elements) {
    /**
     * Computes the number of days in the calendar for the given year.
     * @param year The year
     * @return The number of days
     */
    public int length(int year) {
        return CalendarFunctions.lengthOfYearInDays(this, year);
    }

}

package eras.calendar;

/**
 * A day within a specific element, independent of the year.  Used to mark
 * recurring dates, e.g., new year's day, holidays.
 * @param element The element index, 1 through N
 * @param dayOfElement The day index within the element, 1 through length
 */
public record BareDate(
    int element,
    int dayOfElement
) {
    /**
     * Creates a bar date
     * @param element The element index, 1 through N
     * @param dayOfElement The day index within the element, 1 through length
     * @return The date
     */
    public static BareDate of(int element, int dayOfElement) {
        return new BareDate(element, dayOfElement);
    }

    /**
     * Converts a Date into the equivalent BareDate.
     * @param date The date
     * @return The BareDate
     */
    public static BareDate of(Date date) {
        return of(date.element(), date.dayOfElement());
    }
}

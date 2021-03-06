package eras.calendar;

/**
 * A date, relative to a specific CalendarSchema
 * @param year The numerical year
 * @param element The element index, 1 through N
 * @param dayOfElement The day index within the element, 1 through length
 */
public record Date(
    int year,
    int element,
    int dayOfElement
) {
    /**
     * Creates a date
     * @param year The numerical year
     * @param element The element index, 1 through N
     * @param dayOfElement The day index within the element, 1 through length
     * @return The date
     */
    public static Date of(int year, int element, int dayOfElement) {
        return new Date(year, element, dayOfElement);
    }

    /**
     * Creates a Date given a year and a BareDate.
     * @param bareDate The bare date
     * @return The Date
     */
    public static Date of(int year, BareDate bareDate) {
        return of(year, bareDate.element(), bareDate.dayOfElement());
    }
}

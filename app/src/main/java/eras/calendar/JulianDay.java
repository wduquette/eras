package eras.calendar;

/**
 * A Julian day in a particular year.
 * @param year The numerical year
 * @param dayOfYear The Julian day in that year, 1 to daysInYear.
 */
public record JulianDay(int year, int dayOfYear) {
    /**
     * A factory for building a Julian day in a particular year
     * @param year The numerical year
     * @param dayOfYear The Julian day in that year
     */
    public static JulianDay of(int year, int dayOfYear) {
        return new JulianDay(year, dayOfYear);
    }
}

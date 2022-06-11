package eras.era;

/**
 * A Month is a period of time having a name, an abbreviation, and a length
 * in days.
 * @param name The name of the month
 * @param abbreviation The abbreviated name
 * @param length The length of the month in days
 */
public record Month(String name, String abbreviation, int length) {
    @Override public String toString() {
        return name();
    }
}

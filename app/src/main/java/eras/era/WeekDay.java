package eras.era;

/**
 * A WeekDay defines a day in a WeeklyCycle: its name and normal abbreviation.
 * @param name
 * @param abbreviation
 */
public record WeekDay(String name, String abbreviation) {
    @Override public String toString() {
        return name;
    }
}

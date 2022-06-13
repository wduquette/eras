package eras.basics;

import java.util.List;

/**
 * This class defines the pattern of a year.  The precise content and number
 * of days in the year can depend on the numerical year.
 */
public record YearlyCycle(List<YearElement> elements) {
}

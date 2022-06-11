package eras.era;

public record ReferenceDate(
    int year,          // 1 to N; -1 to -N
    int monthOfYear,   // 1 to monthsPerYear
    int dayOfMonth,    // 1 to daysPerMonth(monthOfYear)
    int dayOfWeek      // 1 to daysPerWeek
) {
}

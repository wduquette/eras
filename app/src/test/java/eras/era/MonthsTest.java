package eras.era;

import eras.Ted;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static eras.checker.Checker.*;

public class MonthsTest extends Ted {
    Month jan = new Month("January", "Jan", 31);
    Month feb = new Month("February", "Feb", 28);
    Month mar = new Month("March", "Mar", 31);
    Month apr = new Month("April", "Apr", 30);
    Month may = new Month("May", "May", 31);
    Month jun = new Month("June", "Jun", 30);
    Month jul = new Month("July", "Jul", 31);
    Month aug = new Month("August", "Aug", 31);
    Month sep = new Month("September", "Sep", 30);
    Month oct = new Month("October", "Oct", 31);
    Month nov = new Month("November", "Nov", 30);
    Month dec = new Month("December", "Dec", 31);
    Months cycle = new Months(List.of(
        jan, feb, mar, apr, may, jun, jul, aug, sep, oct, nov, dec
    ));

    @Test public void testGet_integer() {
        title("testGet_integer");

        require(cycle.get(1).name(), eq("January"));
        require(cycle.get(2).name(), eq("February"));
        require(cycle.get(12).name(), eq("December"));
        require(cycle.get(13).name(), eq("January"));
        require(cycle.get(0).name(), eq("December"));
        require(cycle.get(-11).name(), eq("January"));
    }

    @Test public void testGet_name() {
        title("testGet_name");

        // Normal lookup: name and abbreviation
        require(cycle.get("March"), eq(Optional.of(mar)));
        require(cycle.get("Mar"), eq(Optional.of(mar)));

        // Lookup is case-insensitive
        require(cycle.get("march"), eq(Optional.of(mar)));
        require(cycle.get("mar"), eq(Optional.of(mar)));

        // Unknown items aren't found.
        require(cycle.get("nonesuch").orElse(null), isNull());
    }

    @Test public void testIndexOf_month() {
        title("testIndexOf_month");

        for (int i = 1; i <= cycle.monthsPerYear(); i++) {
            require(cycle.indexOf(cycle.get(i)), eq(i));
        }
    }

    @Test public void testIndexOf_name() {
        title("testIndexOf_name");

        for (int i = 1; i <= cycle.monthsPerYear(); i++) {
            require(cycle.indexOf(cycle.get(i).name()).orElse(null), eq(i));
        }

        require(cycle.indexOf("nonesuch").orElse(null), isNull());
    }

    @Test public void testMonthsPerYear() {
        title("testMonthsPerYear");
        require(cycle.monthsPerYear(), eq(cycle.months().size()));
    }

    @Test public void testDaysPerYear() {
        title("testDaysPerYear");
        require(cycle.daysPerYear(), eq(365));
    }
}

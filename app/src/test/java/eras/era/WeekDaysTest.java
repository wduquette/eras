package eras.era;

import java.util.List;
import java.util.Optional;

import eras.Ted;
import org.junit.Test;
import static eras.checker.Checker.*;

public class WeekDaysTest extends Ted {
    WeekDay sun = new WeekDay("Sunday", "Sun");
    WeekDay mon = new WeekDay("Monday", "Mon");
    WeekDay tue = new WeekDay("Tuesday", "Tue");
    WeekDay wed = new WeekDay("Wednesday", "Wed");
    WeekDay thu = new WeekDay("Thursday", "Thu");
    WeekDay fri = new WeekDay("Friday", "Fri");
    WeekDay sat = new WeekDay("Saturday", "Sat");
    WeekDays cycle = new WeekDays(List.of(
        sun, mon, tue, wed, thu, fri, sat
    ));

    @Test public void testWeekDaysIsImmutable() {
        title("testWeekDaysIsImmutable");

        requireThrow(() -> cycle.weekDays().remove(0));
    }

    @Test public void testGet_integer() {
        title("testGet_integer");

        require(cycle.get(1), eq(sun));
        require(cycle.get(2), eq(mon));
        require(cycle.get(7), eq(sat));
        require(cycle.get(8), eq(sun));
        require(cycle.get(0), eq(sat));
        require(cycle.get(-1), eq(fri));
        require(cycle.get(-6), eq(sun));
    }

    @Test public void testGet_name() {
        title("testGet_name");

        // Normal lookup: name and abbreviation
        require(cycle.get("Friday"), eq(Optional.of(fri)));
        require(cycle.get("Fri"), eq(Optional.of(fri)));

        // Lookup is case-insensitive
        require(cycle.get("friday"), eq(Optional.of(fri)));
        require(cycle.get("fri"), eq(Optional.of(fri)));

        // Unknown items aren't found.
        require(cycle.get("nonesuch").orElse(null), isNull());
    }

    @Test public void testIndexOf_day() {
        title("testIndexOf_day");

        for (int i = 1; i <= cycle.daysPerWeek(); i++) {
            require(cycle.indexOf(cycle.get(i)), eq(i));
        }
    }

    @Test public void testIndexOf_name() {
        title("testIndexOf_name");

        for (int i = 1; i <= cycle.daysPerWeek(); i++) {
            require(cycle.indexOf(cycle.get(i).name()).orElse(null), eq(i));
        }

        require(cycle.indexOf("nonesuch").orElse(null), isNull());
    }

    @Test public void testDaysPerWeek() {
        title("testDaysPerWeek");
        require(cycle.daysPerWeek(), eq(7));
    }
}

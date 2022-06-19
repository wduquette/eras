package eras.util;

import eras.Ted;
import org.junit.Test;

import java.util.List;
import static org.junit.Assert.assertEquals;
import static eras.util.Functions.*;
import static eras.checker.Checker.*;

public class FunctionsTest extends Ted {
    @Test public void testSignature() {
        title("testSignature");

        require(signature("cond"), eq("cond()"));
        require(signature("cond", 1), eq("cond(1)"));
        require(signature("cond", 1, 2), eq("cond(1, 2)"));
        require(signature("cond", "A", "B"), eq("cond(\"A\", \"B\")"));
        require(signature("cond"), eq("cond()"));
        require(signature("cond"), eq("cond()"));
        require(signature("cond", "Hello, \"World\"!"),
            eq("cond(\"Hello, \\\"World\\\"!\")"));
    }

    @Test public void testMap() {
        title("testMap");

        List<Integer> numbers = List.of(1, 2, 3);
        List<String> strings = map(numbers, Object::toString);

        require(strings, eq(List.of("1", "2", "3")));
    }

    @Test public void testMod() {
        title("testMod");
        require(mod(0,5), eq(0));
        require(mod(3,5), eq(3));
        require(mod(5,5), eq(0));
        require(mod(8,5), eq(3));
        require(mod(-1,5), eq(4));
    }

    @Test public void testFmod() {
        title("testFmod");
        require (fmod(0.0, 5.0), hasString("%.1f", "0.0"));
        require (fmod(3.0, 5.0), hasString("%.1f", "3.0"));
        require (fmod(8.0, 5.0), hasString("%.1f", "3.0"));
        require (fmod(-1.0, 5.0), hasString("%.1f", "4.0"));
        require (fmod(-2.0, 5.0), hasString("%.1f", "3.0"));
        require (fmod(-5.0, 5.0), hasString("%.1f", "0.0"));
    }


}

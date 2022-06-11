package eras.checker;

import java.util.List;

import eras.Ted;
import org.junit.Test;
import static eras.checker.Checker.*;
import static org.junit.Assert.*;

public class CheckerTest extends Ted {
    @Test public void testFailure() {
        title("testFailure");

        requireThrow(() -> failure("simulated")
            , isa(AssertionError.class)
        );
    }

    @Test public void testRequire() {
        // Some requires that work
        require(true, eq(true), isTrue());
        require("abc", eq("abc"), isNotNull());

        requireThrow(() -> require(true, eq(false))
            , isa(AssertionError.class)
        );
    }
}

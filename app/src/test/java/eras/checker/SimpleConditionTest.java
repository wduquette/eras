package eras.checker;

import eras.Ted;
import org.junit.Test;

import static eras.checker.Checker.*;

public class SimpleConditionTest extends Ted {
    Condition<String> condition = new SimpleCondition<>(
        "startsWith", s -> s != null && s.startsWith("ABC")
    );

    @Test public void testToString() {
        title("testToString");

        require(condition.toString(), eq("startsWith"));
    }

    @Test public void testTest() {
        title("testTest");

        require(condition.test("ABCDEFG"), isTrue());
        require(condition.test("BCDEFG"), isFalse());
    }

    @Test public void testCheck() throws Exception {
        title("testCheck");

        // Shouldn't throw
        condition.check("ABCDEF");

        // Should throw
        requireThrow(() -> condition.check("BCDEFG")
            , isa(CheckFailureException.class)
        );
    }
}

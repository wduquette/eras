package eras.checker;

import java.util.function.Predicate;

public record SimpleCondition<Value>(
    String signature,
    Predicate<Value> predicate
) implements Checker.Condition<Value> {
    @Override
    public boolean test(Value value) {
        return predicate.test(value);
    }

    @Override
    public void check(Value value) throws CheckFailureException {
        if (!test(value)) {
            throw new CheckFailureException(value, this);
        }
    }

    @Override
    public String toString() {
        return signature();
    }
}

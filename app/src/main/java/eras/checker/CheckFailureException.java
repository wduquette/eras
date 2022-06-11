package eras.checker;

public class CheckFailureException extends Exception {
    private final Object value;
    private final SimpleCondition<?> condition;

    public CheckFailureException(Object value, SimpleCondition<?> condition) {
        super(condition.signature());
        this.value = value;
        this.condition = condition;
    }

    public Object value() {
        return value;
    }

    public SimpleCondition<?> condition() {
        return condition;
    }
}

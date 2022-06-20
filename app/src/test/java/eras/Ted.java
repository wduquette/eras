package eras;

/** The "Test Execution Deputy" */
public class Ted {
    public void title(String title) {
        println("\n---- " + title);
    }

    public void println(String text) {
        System.out.println(text);
    }

    public void printf(String fmt, Object... args) {
        System.out.printf(fmt, args);
    }
}

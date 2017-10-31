package io.github.sskorol.facade;

/**
 * Author: io.github.sskorol
 */
public class Employee {

    private final boolean hired;

    public Employee() {
        hired = true;
    }

    public boolean isHired() {
        return hired;
    }

    public static Employee dummy() {
        return new Employee();
    }
}

package com.company.model;

import java.util.function.Function;

public class Employee {
    private final int id;
    private static int count = 1;
    private final String firstName;
    private final String lastName;
    private final CompanyDept dept;
    private final double salaryRate;
    private final double hourQuantity;
    private final double totalSalary;
    private final boolean pensioner;

    public Employee(String firstName, String lastName, CompanyDept dept, double salaryRate, double hourQuantity) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dept = dept;
        this.salaryRate = salaryRate;
        this.hourQuantity = hourQuantity;
        this.totalSalary = salaryRate * hourQuantity;
        this.pensioner = false;
        id = count++;
    }

    public double getTaxValues(Function<Double, Double> taxCalculator) {
        return taxCalculator.apply(totalSalary);
    }

    public boolean ispensioner() {
        return pensioner;
    }

    public String getFirstname() {
        return firstName;
    }

    public String getLastname() {
        return lastName;
    }

    public double getTotsal() {
        return totalSalary;
    }

    public CompanyDept getDept() {
        return dept;
    }

    @Override
    public String toString() {
        return "Employee: " +
                "id=" + id +
                ", firstname='" + firstName + '\'' +
                ", lastname='" + lastName + '\'' +
                ", dept=" + dept +
                ", salrate=" + salaryRate +
                ", hourquant=" + hourQuantity +
                ", totsal=" + totalSalary + "\n";
    }
}

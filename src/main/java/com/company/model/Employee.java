package com.company.model;

import java.util.function.Function;

public class Employee {
    private final int id;
    private static int count = 1;
    private final String firstname;
    private final String lastname;
    private final CompanyDept dept;
    private final double salaryrate;
    private final double hourquant;
    private final double totalsalary;
    private final boolean pensioner;

    public Employee(String firstname, String lastname, CompanyDept dept, double salaryrate, double hourquant) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.dept = dept;
        this.salaryrate = salaryrate;
        this.hourquant = hourquant;
        this.totalsalary = salaryrate * hourquant;
        this.pensioner = false;
        id = count++;
    }

    public double getTaxValues(Function<Double, Double> taxCalculator) {
        return taxCalculator.apply(totalsalary);
    }

    public boolean ispensioner() {
        return pensioner;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public double getTotsal() {
        return totalsalary;
    }

    public CompanyDept getDept() {
        return dept;
    }

    @Override
    public String toString() {
        return "Employee: " +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", dept=" + dept +
                ", salrate=" + salaryrate +
                ", hourquant=" + hourquant +
                ", totsal=" + totalsalary + "\n";
    }
}

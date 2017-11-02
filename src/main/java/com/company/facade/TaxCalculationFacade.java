package com.company.facade;


import com.company.model.*;
import one.util.streamex.StreamEx;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

public class TaxCalculationFacade {
    private final List<Employee> employees;
    private final IndirectTax indirtax;
    private final DirectTaxes directtax;

    public TaxCalculationFacade() {
        this.employees = asList(new Employee("Katia", "Malakhova", CompanyDept.WAREHOUSE, 25, 40),
                new Employee("Alex", "Popov", CompanyDept.PRODUCTION, 35, 30),
                new Employee("Lyudmila", "Kotova", CompanyDept.PRODUCTION, 30, 65),
                new Employee("Elena", "Tolkacheva", CompanyDept.ACCOUNTING, 32, 50));

        this.indirtax = new IndirectTax(0.22, Taxname.SSC);
        this.directtax = new DirectTaxes(0.18, Taxname.INCOMETAX);
    }

    public double totalSumSalCulc() {
        return StreamEx.of(employees)
                .mapToDouble(Employee::getTotsal)
                .sum();
    }

    public void printTotalSumSal() {
        System.out.println(totalSumSalCulc());
    }

    public double indirectTaxSumCalc() {
        return employees.stream()
                .mapToDouble(v -> v.getTaxValues(indirtax::calculateTax))
                .sum();
    }

    public void printIndTaxSum() {
        System.out.println(indirectTaxSumCalc());
    }

    public double directTaxSumCalc() {
        return StreamEx.of(employees)
                .mapToDouble(t -> t.getTaxValues(directtax::calculateTax))
                .sum();
    }

    public void printDirectTaxSum() {
        System.out.println(directTaxSumCalc());
    }

    public void employeeSalCalc() {
        final Map<String, Double> employeeSalary = employees.stream().collect(Collectors.toMap(employee -> employee.getFirstname().concat(" " + employee.getLastname()), Employee::getTotsal));
        System.out.println(employeeSalary);
    }

    public Employee findEmployee(final Predicate<Employee> condition) {
        return StreamEx.of(employees).findFirst(condition).orElseThrow(() -> new IllegalStateException("No items found"));
    }

    public void netSalarySumCalc() {
        final double netsalarysum = employees.stream()
                .mapToDouble(((s) -> s.getTaxValues((d) -> s.getTotsal() - directtax.calculateTax(s.getTotsal())
                ))).sum();
        System.out.println(netsalarysum);
    }

    public void sumSalarybyDeptCulc(int condition) {
        if (condition == 1) {
            double totalSalarybyDept = StreamEx.of(employees)
                    .filter(employee -> employee.getDept() == CompanyDept.PRODUCTION)
                    .mapToDouble(Employee::getTotsal).sum();
            System.out.println("Total salary sum by select Department is:  " + totalSalarybyDept);
        } else if (condition == 2) {
            double totalSalarybyDept = StreamEx.of(employees)
                    .filter(employee -> employee.getDept() == CompanyDept.WAREHOUSE)
                    .mapToDouble(Employee::getTotsal).sum();
            System.out.println("Total salary sum by select Department is:  " + totalSalarybyDept);
        } else {
            double totalSalarybyDept = StreamEx.of(employees)
                    .filter(employee -> employee.getDept() == CompanyDept.ACCOUNTING)
                    .mapToDouble(Employee::getTotsal).sum();
            System.out.println("Total salary sum by select Department is:  " + totalSalarybyDept);
        }
    }

    public void getAllEmployees() {
        System.out.println(this.employees);
    }

}














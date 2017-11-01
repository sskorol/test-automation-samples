package com.company.PreviousProject;

import com.company.facade.TaxCalculationFacade;
import com.company.model.Employee;

import java.util.Scanner;

import static io.vavr.API.*;

public class Main {
    private static void printMenu() {
        System.out.println("\nSelect an option:\n" +
                "1. Employees list. \n" +
                "2. Accrued salaries of each employee. \n" +
                "3. Total sum of accrued salaries. \n" +
                "4. Total sum of direct taxes. \n" +
                "5. Total sum of indirect taxes. \n" +
                "6. The net salary sum to pay. \n" +
                "7. Total salary sum by select department. \n" +
                "8. Check availability of pensioner employees. \n" +
                "9. Exit. \n");
    }

    public static void main(String[] args) {
        final TaxCalculationFacade taxCalculationFacade = new TaxCalculationFacade();
        try (Scanner console = new Scanner(System.in)) {
            while (true) {
                printMenu();
                int input = console.nextInt();
                Match(input).of(
                        Case($(1), () -> run(taxCalculationFacade::getAllEmployees)),
                        Case($(2), () -> run(taxCalculationFacade::employeeSalCalc)),
                        Case($(3), () -> run(taxCalculationFacade::printTotalSumSal)),
                        Case($(4), () -> run(taxCalculationFacade::printDirectTaxSum)),
                        Case($(5), () -> run(taxCalculationFacade::printIndTaxSum)),
                        Case($(6), () -> run(taxCalculationFacade::netSalarySumCalc)),
                        Case($(7), () -> run(() -> {
                            System.out.println("Select the department for calculation: " + "\n" + "1 -> PRODUCTION" + "\n" + "2 -> WAREHOUSE" + "\n" + "3 -> ACCOUNTING");
                            taxCalculationFacade.sumSalarybyDeptCulc(console.nextInt());
                        })),
                        Case($(8), () -> (taxCalculationFacade.findEmployee(Employee::ispensioner))),
                        Case($(9), () -> run(() -> System.exit(0))),
                        Case($(), () -> run(() -> System.out.println("There is no command found matching your input")))
                );
            }
        }
    }
}
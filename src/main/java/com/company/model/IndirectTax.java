package com.company.model;

public class IndirectTax extends Taxes {
    public IndirectTax(double taxrate, Taxname ssc) {
        super(taxrate);
    }


    @Override
    public double calculateTax(double totalsalary) {
        return totalsalary * getTaxrate();
    }
}

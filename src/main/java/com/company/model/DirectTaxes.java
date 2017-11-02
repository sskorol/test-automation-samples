package com.company.model;

public class DirectTaxes extends Taxes {
    public DirectTaxes(double taxrate, Taxname incometax) {
        super(taxrate);
    }

    @Override
    public double calculateTax(double totalsalary) {
        return (totalsalary - 200) * getTaxrate();
    }
}

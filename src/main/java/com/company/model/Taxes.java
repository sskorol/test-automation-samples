package com.company.model;

public abstract class Taxes {

    private Taxname taxname;
    private final double taxrate;
    private double taxbase;


    public Taxes(double taxrate) {
        this.taxrate = taxrate;
    }

    public double getTaxrate() {
        return taxrate;
    }

    public abstract double calculateTax(double totalsalary);

    @Override
    public String toString() {
        return "Taxe{" +
                "taxname=" + taxname +
                ", taxrate=" + taxrate +
                '}';
    }
}



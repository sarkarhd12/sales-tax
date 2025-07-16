package com.example.salestax.model;

public class Product {
    private final String name;
    private final double basePrice;
    private final boolean imported;
    private final boolean exempt;

    public Product(String name, double basePrice, boolean imported, boolean exempt) {
        this.name = name;
        this.basePrice = basePrice;
        this.imported = imported;
        this.exempt = exempt;
    }

    public String getName() { return name; }
    public double getBasePrice() { return basePrice; }
    public boolean isImported() { return imported; }
    public boolean isExempt() { return exempt; }
}

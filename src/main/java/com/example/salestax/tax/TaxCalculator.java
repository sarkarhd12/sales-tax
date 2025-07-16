package com.example.salestax.tax;

import com.example.salestax.model.Product;

public class TaxCalculator {
    public double calculateTax(Product product) {
        double taxRate = 0.0;
        if (!product.isExempt()) taxRate += 0.10;
        if (product.isImported()) taxRate += 0.05;

        double rawTax = product.getBasePrice() * taxRate;
        return roundUpToNearest005(rawTax);
    }

    private double roundUpToNearest005(double amount) {
        return Math.ceil(amount * 20.0) / 20.0;
    }
}

package com.example.salestax.receipt;

import java.util.ArrayList;
import java.util.List;

public class Receipt {
    private final List<String> lines = new ArrayList<>();
    private double total = 0.0;
    private double totalTaxes = 0.0;

    public void addLine(String description, double itemTotal, double tax) {
        lines.add(description + ": " + String.format("%.2f", itemTotal));
        total += itemTotal;
        totalTaxes += tax;
    }

    public void print() {
        for (String line : lines) {
            System.out.println(line);
        }
        System.out.println("Sales Taxes: " + String.format("%.2f", totalTaxes));
        System.out.println("Total: " + String.format("%.2f", total));
    }
}

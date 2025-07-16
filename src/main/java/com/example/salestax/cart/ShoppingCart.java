package com.example.salestax.cart;

import com.example.salestax.model.CartItem;
import com.example.salestax.model.Product;
import com.example.salestax.receipt.Receipt;
import com.example.salestax.tax.TaxCalculator;

import java.util.List;

public class ShoppingCart {
    private final List<CartItem> items;
    private final TaxCalculator taxCalculator = new TaxCalculator();

    public ShoppingCart(List<CartItem> items) {
        this.items = items;
    }

    public Receipt checkout() {
        Receipt receipt = new Receipt();

        for (CartItem item : items) {
            Product product = item.getProduct();
            double basePrice = product.getBasePrice();
            double tax = taxCalculator.calculateTax(product);
            double itemTotal = (basePrice + tax) * item.getQuantity();

            String desc = item.getQuantity() + " "
                    + (product.isImported() ? "imported " : "")
                    + product.getName();

            receipt.addLine(desc, itemTotal, tax * item.getQuantity());
        }

        return receipt;
    }
}

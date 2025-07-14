package com.example.salestax.service;

import com.example.salestax.model.Item;
import com.example.salestax.model.Receipt;
import java.util.Locale;

public class ReceiptPrinter {

    private static final Locale LOCALE = Locale.US;   // dot decimal

    public String print(Receipt receipt) {
        StringBuilder sb = new StringBuilder();
        for (Item i : receipt.items()) {
            sb.append(i.quantity())
              .append(i.imported() ? " imported " : " ")
              .append(i.name())
              .append(": ")
              .append(String.format(LOCALE, "%.2f", i.lineTotal()))
              .append(System.lineSeparator());
        }
        sb.append("Sales Taxes: ")
          .append(String.format(LOCALE, "%.2f", receipt.totalTaxes()))
          .append(System.lineSeparator());
        sb.append("Total: ")
          .append(String.format(LOCALE, "%.2f", receipt.totalAmount()));
        return sb.toString();
    }
}

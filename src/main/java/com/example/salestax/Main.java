package com.example.salestax;

import com.example.salestax.model.Item;
import com.example.salestax.model.Receipt;
import com.example.salestax.service.InputParser;
import com.example.salestax.service.ReceiptPrinter;
import com.example.salestax.service.TaxCalculator;
import com.example.salestax.tax.BasicSalesTaxRule;
import com.example.salestax.tax.ImportDutyTaxRule;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final TaxCalculator TAX_CALCULATOR =
            new TaxCalculator(List.of(new BasicSalesTaxRule(), new ImportDutyTaxRule()));
    private static final InputParser PARSER = new InputParser();
    private static final ReceiptPrinter PRINTER = new ReceiptPrinter();

    public static void main(String[] args) throws Exception {

        System.out.println("Paste your shopping baskets. Separate baskets with a blank line, " +
                "Ctrl‑D (Unix/macOS) or Ctrl‑Z + Enter (Windows) to finish:");
        List<String> rawLines = new BufferedReader(new InputStreamReader(System.in))
                .lines().toList();

        PARSER.readBaskets(rawLines).forEach(Main::handleBasket);
    }

    private static void handleBasket(List<String> basketLines) {
        List<Item> processed = new ArrayList<>();

        for (String line : basketLines) {
            Item parsed = PARSER.parse(line);
            BigDecimal taxPerUnit = TAX_CALCULATOR.taxFor(parsed);
            BigDecimal lineTotal = parsed.shelfPrice()
                    .add(taxPerUnit)
                    .multiply(BigDecimal.valueOf(parsed.quantity()));
            processed.add(new Item(parsed.quantity(),
                    parsed.name(),
                    parsed.category(),
                    parsed.imported(),
                    parsed.shelfPrice(),
                    taxPerUnit.multiply(BigDecimal.valueOf(parsed.quantity())),
                    lineTotal));
        }

        BigDecimal sumTax   = processed.stream().map(Item::tax).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal sumTotal = processed.stream().map(Item::lineTotal).reduce(BigDecimal.ZERO, BigDecimal::add);

        Receipt receipt = new Receipt(processed, sumTax, sumTotal);

        System.out.println();
        System.out.println(PRINTER.print(receipt));
        System.out.println();
    }
}

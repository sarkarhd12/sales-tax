// Placeholder content for TaxCalculatorTest.java
package com.example.salestax;

import com.example.salestax.model.Category;
import com.example.salestax.model.Item;
import com.example.salestax.service.TaxCalculator;
import com.example.salestax.tax.BasicSalesTaxRule;
import com.example.salestax.tax.ImportDutyTaxRule;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class TaxCalculatorTest {

    private final TaxCalculator calc =
            new TaxCalculator(List.of(new BasicSalesTaxRule(), new ImportDutyTaxRule()));

    @Test
    void chocolateIsOnlyImportTax() {
        Item i = new Item(1, "box of chocolates", Category.FOOD, true,
                new BigDecimal("10.00"), null, null);
        assertEquals(new BigDecimal("0.50"), calc.taxFor(i));
    }

    @Test
    void cdGetsBasicAndImportTax() {
        Item cd = new Item(1, "music CD", Category.OTHER, true,
                new BigDecimal("14.99"), null, null);
        // 10 % = 1.499 → 1.50 ; 5 % = 0.7495 → 0.75 ; total = 2.25
        assertEquals(new BigDecimal("2.25"), calc.taxFor(cd));
    }
}
package com.example.salestax.tax;

import com.example.salestax.model.Category;
import com.example.salestax.model.Item;
import java.math.BigDecimal;
import java.math.RoundingMode;
import static java.math.BigDecimal.*;

public class BasicSalesTaxRule implements TaxRule {

    private static final BigDecimal RATE = new BigDecimal("0.10");

    @Override
    public BigDecimal apply(Item item) {
        if (item.category() == Category.OTHER) {
            return roundUpToNearestFiveCents(item.shelfPrice().multiply(RATE));
        }
        return ZERO;
    }

    private static BigDecimal roundUpToNearestFiveCents(BigDecimal value) {
        return value
                .multiply(new BigDecimal("20"))
                .setScale(0, RoundingMode.CEILING)
                .divide(new BigDecimal("20"), 2, RoundingMode.UNNECESSARY);
    }
}

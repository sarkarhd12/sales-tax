package com.example.salestax.tax;

import com.example.salestax.model.Item;
import java.math.BigDecimal;

public interface TaxRule {
    BigDecimal apply(Item item);
}

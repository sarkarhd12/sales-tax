package com.example.salestax.service;

import com.example.salestax.model.Item;
import com.example.salestax.tax.TaxRule;
import java.math.BigDecimal;
import java.util.List;

public class TaxCalculator {

    private final List<TaxRule> rules;

    public TaxCalculator(List<TaxRule> rules) {
        this.rules = rules;
    }

    /** Returns the combined tax for *one* unit. */
    public BigDecimal taxFor(Item item) {
        return rules.stream()
                    .map(rule -> rule.apply(item))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}

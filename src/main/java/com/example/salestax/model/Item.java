package com.example.salestax.model;

import java.math.BigDecimal;

public record Item(int quantity,
                   String name,
                   Category category,
                   boolean imported,
                   BigDecimal shelfPrice,
                   BigDecimal tax,
                   BigDecimal lineTotal) {
}

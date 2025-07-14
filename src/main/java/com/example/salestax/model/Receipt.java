package com.example.salestax.model;

import java.math.BigDecimal;
import java.util.List;

public record Receipt(List<Item> items,
                      BigDecimal totalTaxes,
                      BigDecimal totalAmount) {
}

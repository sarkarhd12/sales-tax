package com.example.salestax.service;

import com.example.salestax.model.Category;
import com.example.salestax.model.Item;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputParser {

    private static final Pattern LINE =
            Pattern.compile("(?<qty>\\d+)\\s+(?<name>.+)\\s+at\\s+(?<price>\\d+\\.\\d{2})");
    public List<List<String>> readBaskets(List<String> consoleLines) {
        List<List<String>> baskets = new ArrayList<>();
        List<String> current = new ArrayList<>();
        for (String line : consoleLines) {
            if (line.isBlank()) {
                if (!current.isEmpty()) {
                    baskets.add(current);
                    current = new ArrayList<>();
                }
            } else {
                current.add(line.trim());
            }
        }
        if (!current.isEmpty()) baskets.add(current);
        return baskets;
    }

    public Item parse(String line) {
        Matcher m = LINE.matcher(line);
        if (!m.matches()) {
            throw new IllegalArgumentException("Cannot understand line: " + line);
        }
        int qty = Integer.parseInt(m.group("qty"));
        String rawName = m.group("name");
        BigDecimal price = new BigDecimal(m.group("price"));

        boolean imported = rawName.contains("imported");
        String cleanName = rawName.replace("imported", "").trim();

        Category category = classify(cleanName);

        return new Item(qty, cleanName, category, imported, price, null, null);
    }

    private static Category classify(String name) {
        String n = name.toLowerCase();
        if (n.contains("book")) return Category.BOOK;
        if (n.contains("chocolate")) return Category.FOOD;
        if (n.contains("pill")) return Category.MEDICAL;
        return Category.OTHER;
    }
}

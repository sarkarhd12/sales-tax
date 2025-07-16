package com.example.salestax.io;

import com.example.salestax.model.CartItem;
import com.example.salestax.model.Product;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class InputParser {
    private final String fileName;

    public InputParser(String fileName) {
        this.fileName = fileName;
    }

    public List<List<CartItem>> parse() {
        List<List<CartItem>> allCarts = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            List<CartItem> currentCart = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (line.isEmpty()) {
                    if (!currentCart.isEmpty()) {
                        allCarts.add(currentCart);
                        currentCart = new ArrayList<>();
                    }
                    continue;
                }

                CartItem item = parseLine(line);
                if (item != null) currentCart.add(item);
            }

            if (!currentCart.isEmpty()) {
                allCarts.add(currentCart);
            }

        } catch (Exception e) {
            System.err.println("Failed to read input: " + e.getMessage());
        }

        return allCarts;
    }

    private CartItem parseLine(String line) {
        try {
            String[] parts = line.split(" at ");
            double price = Double.parseDouble(parts[1]);

            String[] left = parts[0].split(" ", 2);
            int quantity = Integer.parseInt(left[0]);
            String name = left[1];

            boolean isImported = name.contains("imported");
            boolean isExempt = isExemptProduct(name);

            name = name.replace("imported ", "").trim();

            Product product = new Product(name, price, isImported, isExempt);
            return new CartItem(product, quantity);
        } catch (Exception e) {
            System.err.println("Invalid line: " + line);
            return null;
        }
    }

    private boolean isExemptProduct(String name) {
        name = name.toLowerCase();
        return name.contains("book") || name.contains("chocolate") || name.contains("pill");
    }
}

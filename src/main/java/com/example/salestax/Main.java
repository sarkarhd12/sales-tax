package com.example.salestax;

import com.example.salestax.io.InputParser;
import com.example.salestax.cart.CartProcessor;
import com.example.salestax.model.CartItem;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String fileName = "input.txt";

        InputParser parser = new InputParser(fileName);
        List<List<CartItem>> allCarts = parser.parse();

        CartProcessor processor = new CartProcessor();
        processor.processCarts(allCarts);
    }
}

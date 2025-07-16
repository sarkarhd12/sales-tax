package com.example.salestax.cart;

import com.example.salestax.model.CartItem;
import com.example.salestax.receipt.Receipt;

import java.util.List;

public class CartProcessor {
    public void processCarts(List<List<CartItem>> allCarts) {
        int index = 1;
        for (List<CartItem> items : allCarts) {
            System.out.println("Output " + index++ + ":");
            ShoppingCart cart = new ShoppingCart(items);
            Receipt receipt = cart.checkout();
            receipt.print();
            System.out.println();
        }
    }
}
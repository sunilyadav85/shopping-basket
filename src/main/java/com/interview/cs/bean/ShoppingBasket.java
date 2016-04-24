package com.interview.cs.bean;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class ShoppingBasket<T extends ShoppingItem> {

    private BigDecimal price = BigDecimal.ZERO;
    private Map<T, Integer> shopping = new HashMap<>();

    public void addItem(T shoppingItem) {
        addItems(shoppingItem, 1);
    }

    public void addItems(T shoppingItem, int quantity) {
        shopping.compute(shoppingItem, (k, v) -> v == null ? quantity : v + quantity);
    }

    public Map<T, Integer> getShopping() {
        return shopping;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void incrementBasketPrice(BigDecimal price) {
        this.price = this.price.add(price);
    }
}

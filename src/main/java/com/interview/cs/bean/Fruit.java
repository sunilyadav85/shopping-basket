package com.interview.cs.bean;

import com.interview.cs.offer.Offer;
import com.interview.cs.service.ShoppingBasketProcessor;

import java.math.BigDecimal;

public class Fruit implements ShoppingItem {

    private final String name;
    private final BigDecimal price;
    private final ShoppingBasket<Fruit> shoppingBasket;
    private Offer offer;

    public Fruit(String name, BigDecimal price, ShoppingBasket<Fruit> shoppingBasket) {
        this(name, price, shoppingBasket, null);
    }

    public Fruit(String name, BigDecimal price, ShoppingBasket<Fruit> shoppingBasket, Offer offer) {
        this.name = name;
        this.price = price;
        this.shoppingBasket = shoppingBasket;
        this.offer = offer;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ShoppingBasket<Fruit> getShoppingBasket() {
        return shoppingBasket;
    }

    public Offer getOffer() {
        return offer;
    }

    @Override
    public void accept(ShoppingBasketProcessor processor) {
        processor.process(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Fruit fruit = (Fruit) o;

        if (!name.equals(fruit.name)) return false;
        return price.equals(fruit.price);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + price.hashCode();
        return result;
    }
}

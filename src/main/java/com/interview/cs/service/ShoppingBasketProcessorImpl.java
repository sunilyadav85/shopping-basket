package com.interview.cs.service;

import com.interview.cs.bean.Fruit;
import com.interview.cs.bean.ShoppingBasket;
import com.interview.cs.offer.Offer;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ShoppingBasketProcessorImpl implements ShoppingBasketProcessor {

    public void process(Fruit fruit) {
        Offer offer = fruit.getOffer();
        if (offer != null) {
            offer.apply(fruit);
        } else {
            BigDecimal itemPrice = fruit.getPrice();
            ShoppingBasket<Fruit> shoppingBasket = fruit.getShoppingBasket();
            BigDecimal quantity = new BigDecimal(shoppingBasket.getShopping().get(fruit));
            fruit.getShoppingBasket().incrementBasketPrice(itemPrice.multiply(quantity));
        }
    }
}

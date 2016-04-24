package com.interview.cs.offer;

import com.interview.cs.bean.Fruit;
import com.interview.cs.bean.ShoppingBasket;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service("buyOneGetOneOffer")
public class BuyOneGetOneOffer implements Offer {

    @Override
    public void apply(Fruit fruit) {
        BigDecimal itemPrice = fruit.getPrice();
        ShoppingBasket<Fruit> shoppingBasket = fruit.getShoppingBasket();
        int quantity = shoppingBasket.getShopping().get(fruit);

        shoppingBasket.incrementBasketPrice(new BigDecimal(quantity).multiply(itemPrice));
        shoppingBasket.addItems(fruit, quantity);
    }
}

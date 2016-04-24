package com.interview.cs.offer;

import com.interview.cs.bean.Fruit;
import com.interview.cs.bean.ShoppingBasket;
import org.springframework.stereotype.Service;

@Service("threeForPriceTwoOffer")
public class ThreeForPriceTwoOffer implements Offer {

    @Override
    public void apply(Fruit fruit) {
        ShoppingBasket<Fruit> shoppingBasket = fruit.getShoppingBasket();
        int quantity = shoppingBasket.getShopping().get(fruit);

        for (int count = 1; count <= quantity; count++) {
            if (count % 3 != 0) {
                shoppingBasket.incrementBasketPrice(fruit.getPrice());
            }
        }
    }
}

package com.interview.cs.helper;

import com.interview.cs.bean.Fruit;
import com.interview.cs.bean.ShoppingBasket;
import com.interview.cs.enums.FruitType;
import com.interview.cs.offer.Offer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static com.interview.cs.enums.FruitType.*;

@Component
public class ShoppingBasketHelper {

    @Autowired
    @Qualifier("buyOneGetOneOffer")
    private Offer buyOneGetOneOffer;

    @Autowired
    @Qualifier("threeForPriceTwoOffer")
    private Offer threeForPriceTwoOffer;

    public Fruit getFruit(FruitType fruitType, ShoppingBasket<Fruit> shoppingBasket) {
        switch (fruitType) {
            case APPLE:
                return new Fruit(APPLE.getDisplayName(), new BigDecimal(0.35), shoppingBasket);
            case BANANA:
                return new Fruit(BANANA.getDisplayName(), new BigDecimal(0.20), shoppingBasket);
            case MELON:
                return new Fruit(MELON.getDisplayName(), new BigDecimal(0.50), shoppingBasket, buyOneGetOneOffer);
            case LIME:
                return new Fruit(LIME.getDisplayName(), new BigDecimal(0.15), shoppingBasket, threeForPriceTwoOffer);
            default:
                throw new IllegalStateException("Invalid Fruit Type");
        }
    }
}

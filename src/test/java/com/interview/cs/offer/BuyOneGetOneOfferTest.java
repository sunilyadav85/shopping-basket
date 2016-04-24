package com.interview.cs.offer;

import com.interview.cs.app.ShoppingBasketTestConfig;
import com.interview.cs.bean.Fruit;
import com.interview.cs.bean.ShoppingBasket;
import com.interview.cs.helper.ShoppingBasketHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

import static com.interview.cs.enums.FruitType.MELON;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ShoppingBasketTestConfig.class)
public class BuyOneGetOneOfferTest {

    @Autowired
    private BuyOneGetOneOffer classToTest;

    @Autowired
    private ShoppingBasketHelper shoppingBasketHelper;

    @Test
    public void testProcessWithBuyOneGetOneOffer() throws Exception {

        // Given
        ShoppingBasket<Fruit> shoppingBasket = new ShoppingBasket<>();
        Fruit melon = shoppingBasketHelper.getFruit(MELON, shoppingBasket);
        int quantity = 4;
        shoppingBasket.addItems(melon, quantity);

        // When
        classToTest.apply(melon);

        // Then
        int expectedQuantity = quantity * 2;
        assertThat(shoppingBasket.getShopping().get(melon), equalTo(expectedQuantity));
        assertThat(shoppingBasket.getPrice(), equalTo(melon.getPrice().multiply(new BigDecimal(quantity))));
    }
}
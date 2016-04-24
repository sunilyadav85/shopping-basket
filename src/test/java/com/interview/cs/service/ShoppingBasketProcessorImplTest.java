package com.interview.cs.service;

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

import static com.interview.cs.enums.FruitType.*;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ShoppingBasketTestConfig.class)
public class ShoppingBasketProcessorImplTest {

    @Autowired
    private ShoppingBasketProcessorImpl classToTest;

    @Autowired
    private ShoppingBasketHelper shoppingBasketHelper;

    @Test
    public void testProcessWithNoOffers() throws Exception {

        // Given
        ShoppingBasket<Fruit> shoppingBasket = new ShoppingBasket<>();
        Fruit apple = shoppingBasketHelper.getFruit(APPLE, shoppingBasket);
        int quantity = 4;
        shoppingBasket.addItems(apple, quantity);

        // When
        classToTest.process(apple);

        // Then
        assertThat(shoppingBasket.getShopping().get(apple), equalTo(quantity));
        assertThat(shoppingBasket.getPrice(), equalTo(apple.getPrice().multiply(new BigDecimal(quantity))));
    }

    @Test
    public void testProcessWithBuyOneGetOneOffer() throws Exception {

        // Given
        ShoppingBasket<Fruit> shoppingBasket = new ShoppingBasket<>();
        Fruit melon = shoppingBasketHelper.getFruit(MELON, shoppingBasket);
        int quantity = 4;
        shoppingBasket.addItems(melon, quantity);

        // When
        classToTest.process(melon);

        // Then
        int expectedQuantity = quantity * 2;
        assertThat(shoppingBasket.getShopping().get(melon), equalTo(expectedQuantity));
        assertThat(shoppingBasket.getPrice(), equalTo(melon.getPrice().multiply(new BigDecimal(quantity))));
    }

    @Test
    public void testProcessWithThreeForPriceTwoOffer() throws Exception {

        // Given
        ShoppingBasket<Fruit> shoppingBasket = new ShoppingBasket<>();
        Fruit lime = shoppingBasketHelper.getFruit(LIME, shoppingBasket);
        int quantity = 8;
        shoppingBasket.addItems(lime, quantity);

        // When
        classToTest.process(lime);

        // Then
        BigDecimal expectedPrice = lime.getPrice().multiply(new BigDecimal(6));
        assertThat(shoppingBasket.getPrice(), equalTo(expectedPrice));
        assertThat(shoppingBasket.getShopping().get(lime), equalTo(quantity));
    }
}
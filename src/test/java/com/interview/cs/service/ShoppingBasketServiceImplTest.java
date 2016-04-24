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
import java.math.RoundingMode;

import static com.interview.cs.enums.FruitType.*;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ShoppingBasketTestConfig.class)
public class ShoppingBasketServiceImplTest {

    @Autowired
    private ShoppingBasketServiceImpl classToTest;

    @Autowired
    private ShoppingBasketHelper shoppingBasketHelper;

    @Test
    public void testEvaluateBasketPrice() throws Exception {

        // Given
        ShoppingBasket<Fruit> shoppingBasket = new ShoppingBasket<>();
        Fruit apple = shoppingBasketHelper.getFruit(APPLE, shoppingBasket);
        int quantity = 2;
        shoppingBasket.addItems(apple, quantity);
        Fruit melon = shoppingBasketHelper.getFruit(MELON, shoppingBasket);
        quantity = 3;
        shoppingBasket.addItems(melon, quantity);
        Fruit lime = shoppingBasketHelper.getFruit(LIME, shoppingBasket);
        quantity = 4;
        shoppingBasket.addItems(lime, quantity);

        // When
        BigDecimal basketPrice = classToTest.evaluateBasketPrice(shoppingBasket);

        // Then
        BigDecimal expectedPrice = new BigDecimal(2.65).setScale(2, RoundingMode.HALF_UP);
        assertThat(basketPrice, equalTo(expectedPrice));
    }
}
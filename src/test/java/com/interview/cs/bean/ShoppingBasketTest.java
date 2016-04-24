package com.interview.cs.bean;

import com.interview.cs.app.ShoppingBasketTestConfig;
import com.interview.cs.helper.ShoppingBasketHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

import static com.interview.cs.enums.FruitType.APPLE;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ShoppingBasketTestConfig.class)
public class ShoppingBasketTest {

    @Autowired
    private ShoppingBasketHelper shoppingBasketHelper;

    @Test
    public void testAddItem() throws Exception {

        // Given
        ShoppingBasket<Fruit> fruitShoppingBasket = new ShoppingBasket<>();
        Fruit apple = shoppingBasketHelper.getFruit(APPLE, fruitShoppingBasket);

        // When
        fruitShoppingBasket.addItem(apple);

        //Then
        assertThat(fruitShoppingBasket.getShopping().get(apple), equalTo(1));
    }

    @Test
    public void testAddItems() throws Exception {

        // Given
        ShoppingBasket<Fruit> fruitShoppingBasket = new ShoppingBasket<>();
        Fruit apple = shoppingBasketHelper.getFruit(APPLE, fruitShoppingBasket);

        // When
        fruitShoppingBasket.addItems(apple, 4);

        //Then
        assertThat(fruitShoppingBasket.getShopping().get(apple), equalTo(4));
    }

    @Test
    public void testIncrementBasketPrice() throws Exception {

        // Given
        ShoppingBasket<Fruit> fruitShoppingBasket = new ShoppingBasket<>();
        BigDecimal expectedPrice = new BigDecimal(4);

        // When
        fruitShoppingBasket.incrementBasketPrice(expectedPrice);

        //Then
        assertThat(fruitShoppingBasket.getPrice(), equalTo(expectedPrice));
    }
}
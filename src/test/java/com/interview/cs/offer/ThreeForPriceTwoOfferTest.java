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

import static com.interview.cs.enums.FruitType.LIME;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ShoppingBasketTestConfig.class)
public class ThreeForPriceTwoOfferTest {

    @Autowired
    private ThreeForPriceTwoOffer classToTest;

    @Autowired
    private ShoppingBasketHelper shoppingBasketHelper;

    @Test
    public void testApplyThreeForPriceTwoOfferWhenQuantityLessThan3() throws Exception {

        // Given
        ShoppingBasket<Fruit> shoppingBasket = new ShoppingBasket<>();
        Fruit lime = shoppingBasketHelper.getFruit(LIME, shoppingBasket);
        int quantity = 2;
        shoppingBasket.addItems(lime, quantity);

        // When
        classToTest.apply(lime);

        // Then
        BigDecimal expectedPrice = lime.getPrice().multiply(new BigDecimal(quantity));
        assertThat(shoppingBasket.getPrice(), equalTo(expectedPrice));
        assertThat(shoppingBasket.getShopping().get(lime), equalTo(quantity));
    }

    @Test
    public void testApplyThreeForPriceTwoOfferWhenQuantityEqualTo3() throws Exception {

        // Given
        ShoppingBasket<Fruit> shoppingBasket = new ShoppingBasket<>();
        Fruit lime = shoppingBasketHelper.getFruit(LIME, shoppingBasket);
        int quantity = 3;
        shoppingBasket.addItems(lime, quantity);

        // When
        classToTest.apply(lime);

        // Then
        BigDecimal expectedPrice = lime.getPrice().multiply(new BigDecimal(2));
        assertThat(shoppingBasket.getPrice(), equalTo(expectedPrice));
        assertThat(shoppingBasket.getShopping().get(lime), equalTo(quantity));
    }

    @Test
    public void testApplyThreeForPriceTwoOfferWhenQuantityGreaterThan3() throws Exception {

        // Given
        ShoppingBasket<Fruit> shoppingBasket = new ShoppingBasket<>();
        Fruit lime = shoppingBasketHelper.getFruit(LIME, shoppingBasket);
        int quantity = 4;
        shoppingBasket.addItems(lime, quantity);

        // When
        classToTest.apply(lime);

        // Then
        BigDecimal expectedPrice = lime.getPrice().multiply(new BigDecimal(3));
        assertThat(shoppingBasket.getPrice(), equalTo(expectedPrice));
        assertThat(shoppingBasket.getShopping().get(lime), equalTo(quantity));
    }
}
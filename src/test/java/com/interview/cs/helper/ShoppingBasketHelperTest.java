package com.interview.cs.helper;

import com.interview.cs.app.ShoppingBasketTestConfig;
import com.interview.cs.bean.Fruit;
import com.interview.cs.bean.ShoppingBasket;
import com.interview.cs.enums.FruitType;
import com.interview.cs.offer.BuyOneGetOneOffer;
import com.interview.cs.offer.ThreeForPriceTwoOffer;
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
public class ShoppingBasketHelperTest {

    @Autowired
    private ShoppingBasketHelper classToTest;

    @Autowired
    private BuyOneGetOneOffer buyOneGetOneOffer;

    @Autowired
    private ThreeForPriceTwoOffer threeForPriceTwoOffer;

    @Test
    public void testGetApple() throws Exception {

        // Given
        ShoppingBasket<Fruit> shoppingBasket = new ShoppingBasket<>();
        FruitType apple = APPLE;

        // When
        Fruit result = classToTest.getFruit(apple, shoppingBasket);

        // Then
        Fruit expectedApple = new Fruit(apple.getDisplayName(), new BigDecimal(0.35), shoppingBasket);
        assertThat(result, equalTo(expectedApple));
    }

    @Test
    public void testGetBanana() throws Exception {

        // Given
        ShoppingBasket<Fruit> shoppingBasket = new ShoppingBasket<>();
        FruitType banana = FruitType.BANANA;

        // When
        Fruit result = classToTest.getFruit(banana, shoppingBasket);

        // Then
        Fruit expectedBanana = new Fruit(banana.getDisplayName(), new BigDecimal(0.20), shoppingBasket);
        assertThat(result, equalTo(expectedBanana));
    }

    @Test
    public void testGetMelon() throws Exception {

        // Given
        ShoppingBasket<Fruit> shoppingBasket = new ShoppingBasket<>();
        FruitType melon = FruitType.MELON;

        // When
        Fruit result = classToTest.getFruit(melon, shoppingBasket);

        // Then
        Fruit expectedMelon = new Fruit(melon.getDisplayName(), new BigDecimal(0.50), shoppingBasket, buyOneGetOneOffer);
        assertThat(result, equalTo(expectedMelon));
    }

    @Test
    public void testGetLime() throws Exception {

        // Given
        ShoppingBasket<Fruit> shoppingBasket = new ShoppingBasket<>();
        FruitType lime = FruitType.LIME;

        // When
        Fruit result = classToTest.getFruit(lime, shoppingBasket);

        // Then
        Fruit expectedLime = new Fruit(lime.getDisplayName(), new BigDecimal(0.15), shoppingBasket, threeForPriceTwoOffer);
        assertThat(result, equalTo(expectedLime));
    }
}
package com.interview.cs.controller;

import com.interview.cs.app.ShoppingBasketTestConfig;
import com.interview.cs.enums.FruitType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.interview.cs.enums.FruitType.*;
import static java.util.stream.Stream.concat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ShoppingBasketTestConfig.class)
public class ShoppingBasketRestControllerTest {

    @Autowired
    private ShoppingBasketRestController classToTest;

    @Test
    public void testGetShoppingBasketPrice() throws Exception {

        // Given
        Stream<FruitType> apples = Stream.of(APPLE, APPLE, APPLE, APPLE, APPLE);
        Stream<FruitType> bananas = Stream.of(BANANA, BANANA, BANANA);
        Stream<FruitType> melons = Stream.of(MELON, MELON, MELON, MELON);
        Stream<FruitType> limes = Stream.of(LIME, LIME, LIME, LIME, LIME);

        // Create a stream wth 5 X Apple, 3 X Banana, 4 X Melon, 5 X Lime
        Stream<FruitType> fruitTypes = concat(apples, concat(bananas, concat(melons, limes)));

        // When
        BigDecimal basketPrice = classToTest.getShoppingBasketPrice(fruitTypes.collect(Collectors.toList()));

        // Then
        BigDecimal expectedPrice = new BigDecimal(4.95).setScale(2, RoundingMode.HALF_UP);
        assertThat(basketPrice, equalTo(expectedPrice));
    }
}
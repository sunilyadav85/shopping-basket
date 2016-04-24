package com.interview.cs.controller;

import com.interview.cs.bean.Fruit;
import com.interview.cs.bean.ShoppingBasket;
import com.interview.cs.enums.FruitType;
import com.interview.cs.helper.ShoppingBasketHelper;
import com.interview.cs.service.ShoppingBasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class ShoppingBasketRestController {

    @Autowired
    private ShoppingBasketService shoppingBasketService;

    @Autowired
    private ShoppingBasketHelper shoppingBasketHelper;

    @RequestMapping(value = "/basket/price", method = RequestMethod.GET)
    public BigDecimal getShoppingBasketPrice(@RequestParam(name = "fruitTypes", required = true) List<FruitType> fruitTypes) {
        ShoppingBasket<Fruit> fruitShoppingBasket = new ShoppingBasket<>();

        fruitTypes.forEach(fruitType -> fruitShoppingBasket.addItem(shoppingBasketHelper.getFruit(fruitType, fruitShoppingBasket)));
        return shoppingBasketService.evaluateBasketPrice(fruitShoppingBasket);
    }
}

package com.interview.cs.service;


import com.interview.cs.bean.ShoppingBasket;
import com.interview.cs.bean.ShoppingItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class ShoppingBasketServiceImpl implements ShoppingBasketService {

    @Autowired
    private ShoppingBasketProcessor shoppingBasketProcessor;

    public BigDecimal evaluateBasketPrice(ShoppingBasket<? extends ShoppingItem> shoppingBasket) {
        shoppingBasket.getShopping().forEach((k, v) -> k.accept(shoppingBasketProcessor));
        return shoppingBasket.getPrice().setScale(2, RoundingMode.HALF_UP);
    }
}

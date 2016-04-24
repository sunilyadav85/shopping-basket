package com.interview.cs.service;

import com.interview.cs.bean.ShoppingBasket;
import com.interview.cs.bean.ShoppingItem;

import java.math.BigDecimal;

public interface ShoppingBasketService {

    BigDecimal evaluateBasketPrice(ShoppingBasket<? extends ShoppingItem> shoppingBasket);
}

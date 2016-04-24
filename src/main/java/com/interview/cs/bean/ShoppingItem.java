package com.interview.cs.bean;

import com.interview.cs.service.ShoppingBasketProcessor;

public interface ShoppingItem {

    void accept(ShoppingBasketProcessor processor);
}

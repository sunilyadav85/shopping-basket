package com.interview.cs.app;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@Configuration
@ActiveProfiles("test")
@Import(ShoppingBasketConfig.class)
public class ShoppingBasketTestConfig {

}

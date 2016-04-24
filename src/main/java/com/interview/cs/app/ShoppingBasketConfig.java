package com.interview.cs.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.Ordered;

@Configuration
@ComponentScan(
        basePackages = {"com.interview.cs"},
        excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = {ShoppingBasketApp.class})
)
@PropertySource(ignoreResourceNotFound = false, value = {"classpath:shopping-basket-app.properties"})
public class ShoppingBasketConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        propertySourcesPlaceholderConfigurer.setIgnoreUnresolvablePlaceholders(true);
        propertySourcesPlaceholderConfigurer.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return propertySourcesPlaceholderConfigurer;
    }

    @Bean
    public EmbeddedServletContainerFactory embeddedServletContainerFactory(@Value("${shopping.basket.http.port}") int httpPort) {
        return new TomcatEmbeddedServletContainerFactory(httpPort);
    }
}

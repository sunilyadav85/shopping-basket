package com.interview.cs.app;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.interview.cs.bean.Fruit;
import com.interview.cs.bean.ShoppingBasket;
import com.interview.cs.helper.ShoppingBasketHelper;
import com.interview.cs.service.ShoppingBasketService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.interview.cs.enums.FruitType.*;

@SpringBootApplication
public class ShoppingBasketApp implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Autowired
    private ShoppingBasketHelper shoppingBasketHelper;

    @Autowired
    private ShoppingBasketService shoppingBasketService;

    public static void main(String[] args) {

        String appName = System.getProperty("app.name");
        if (appName == null) {
            appName = "ShoppingBasketApp";
        }

        String envProperty = "shopping.basket.environment";
        String env = getArgOrProperty(envProperty, args);
        if (env == null) {
            String errorMsg = String.format("System property %s must be defined for %s.", envProperty, appName);
            throw new IllegalStateException(errorMsg);
        }

        Predicate<String> isParameter = input -> input.startsWith("--");
        List<String> argsList = Arrays.asList(args);
        Collection<String> parameters = Collections2.filter(argsList, isParameter);
        Collection<String> configurations = Collections2.filter(argsList, Predicates.not(isParameter));

        SpringApplication springApplication = new SpringApplication(Lists.asList(ShoppingBasketApp.class, configurations.toArray()).toArray());
        springApplication.setWebEnvironment(getArgOrProperty("shopping.basket.http.port", args) != null);
        springApplication.setAdditionalProfiles(env);
        springApplication.run(parameters.toArray(new String[0]));

        applicationContext.getBean(ShoppingBasketApp.class).printWelcomeMessageAndProcessInput();
    }

    private void printWelcomeMessageAndProcessInput() {
        System.out.println("************Welcome to Shopping Basket app ***************");
        System.out.println("To add Fruit to the basket enter the item number shown below");
        System.out.println("(1) To add Apple to Basket");
        System.out.println("(2) To add Banana to Basket");
        System.out.println("(3) To add Melon to Basket");
        System.out.println("(4) To add Lime to Basket");
        System.out.println("(5) To get price of this basket");
        System.out.println("(6) To Exit the application");
        Scanner scanner = new Scanner(System.in);

        ShoppingBasket<Fruit> shoppingBasket = new ShoppingBasket<>();
        while (scanner.hasNext()) {
            shoppingBasket = processUserInput(scanner, shoppingBasket);
        }
    }

    private ShoppingBasket<Fruit> processUserInput(Scanner scanner, ShoppingBasket<Fruit> shoppingBasket) {
        int input = scanner.nextInt();
        switch (input) {
            case 1:
                shoppingBasket.addItem(shoppingBasketHelper.getFruit(APPLE, shoppingBasket));
                System.out.println("Added ".concat(APPLE.getDisplayName()).concat(" @35p to the basket"));
                break;
            case 2:
                shoppingBasket.addItem(shoppingBasketHelper.getFruit(BANANA, shoppingBasket));
                System.out.println("Added ".concat(BANANA.getDisplayName()).concat(" @20p to the basket"));
                break;
            case 3:
                shoppingBasket.addItem(shoppingBasketHelper.getFruit(MELON, shoppingBasket));
                System.out.println("Added ".concat(MELON.getDisplayName()).concat(" @50p to the basket with buy one get one offer"));
                break;
            case 4:
                shoppingBasket.addItem(shoppingBasketHelper.getFruit(LIME, shoppingBasket));
                System.out.println("Added ".concat(LIME.getDisplayName()).concat(" @15p to the basket with three for price of two offer"));
                break;
            case 5:
                printBasketDetails(shoppingBasket, "******** Items in the Basket Before Processing ********");
                BigDecimal shoppingBasketPrice = shoppingBasketService.evaluateBasketPrice(shoppingBasket);
                printBasketDetails(shoppingBasket, "******** Items in the Basket After Processing ********");
                Currency currency = Currency.getInstance(Locale.UK);
                System.out.println("Basket Price: " + currency + shoppingBasketPrice);
                System.out.println("\n\nCreating new Shopping Basket to process...");
                shoppingBasket = new ShoppingBasket<>();
                break;
            case 6:
                System.out.println("Shutting down Shopping Basket App");
                System.exit(0);
            default:
                System.out.println("Incorrect Value [" + input + "] entered");
                System.out.println("Correct Input Values are [1, 2, 3, 4, 5, 6]");
                break;
        }
        return shoppingBasket;
    }

    private static String getArgOrProperty(String argOrPropertyName, String[] args) {
        Pattern pattern = Pattern.compile(String.format("--%s=(.*)", argOrPropertyName));
        for (String arg : args) {
            Matcher matcher = pattern.matcher(arg);
            if (matcher.matches()) {
                return matcher.group(1);
            }
        }
        return System.getProperty(argOrPropertyName);
    }

    private static void printBasketDetails(ShoppingBasket<Fruit> shoppingBasket, String displayMessage) {
        System.out.println(displayMessage);
        shoppingBasket.getShopping().forEach((k, v) ->
                System.out.println(k.getName() + " -> " + v));
    }

    @Override
    public void setApplicationContext(ApplicationContext appContext) throws BeansException {
        applicationContext = appContext;
    }
}

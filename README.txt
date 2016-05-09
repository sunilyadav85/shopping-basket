************************************** Application Requirements **************************************
Write a simple program that calculates the price of a basket of shopping.
Items are presented one at a time, in a list, identified by name - for example "Apple" or "Banana".
Multiple items are present multiple times in the list, so for example ["Apple", "Apple", "Banana"] is a basket with two apples and one banana.

Items are priced as follows:
- Apples are 35p each
- Bananas are 20p each
- Melons are 50p each, but are available as ‘buy one get one free’
- Limes are 15p each, but are available in a ‘three for the price of two’ offer

Given a list of shopping, calculate the total cost of those items.

******************************************************************************************************


****************************************** Notes *****************************************************
I have used Maven assembly mechanism, Spring boot and REST WebServices to create this application.
This means the application can be run as a standalone application.
Follow the steps below to run this application
 - Navigate to the project directory "shopping-basket" on Commandline and the run the maven command below
 - $ mvn clean install
 - This creates the fat jars, now navigate to folder "../shopping-basket/target/shopping-basket-1.0/bin" and run the command below
 - For unix -> $ sh devShoppingBasket.sh         For Windows -> $ devShoppingBasket.bat
 - This will start the application you could now test it directly in the console by entering the Fruits or use the webservice
 - To call the webservice using a rest client use the URL below
URL - > http://localhost:55511/basket/price?fruitTypes=APPLE,BANANA,MELON,MELON,LIME,LIME,LIME

******************************************************************************************************
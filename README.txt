Note: I have used Maven assembly mechanism, Spring boot and REST WebServices to create this application.
This means the application can be run as a standalone application.
Follow the steps below to run this application
 - Navigate to the project directory "shopping-basket" on Commandline and the run the maven command below
 - $ mvn clean install
 - This creates the fat jars, now navigate to folder "../shopping-basket/target/shopping-basket-1.0/bin" and run the command below
 - For unix -> $ sh devShoppingBasket.sh         For Windows -> $ devShoppingBasket.bat
 - This will start the application you could now test it directly in the console by entering the Fruits or use the webservice
 - To call the webservice using a rest client use the URL below
URL - > http://localhost:55511/basket/price?fruitTypes=APPLE,BANANA,MELON,MELON,LIME,LIME,LIME
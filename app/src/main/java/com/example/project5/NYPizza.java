package com.example.project5;

/**
 * The factory class that creates NYPizzas
 * @author Jake Michalowicz, Young Ye
 */
public class NYPizza implements PizzaFactory{
    /**
     * Creates a deluxe pizza
     * @return Pizza object
     */
    @Override
    public Pizza createDeluxe() {
        Pizza newPizza = new Deluxe();
        String[] toppings = MainActivity.getToppingsList();
        for(int i = 0; i < toppings.length; i++){
            newPizza.add(Topping.valueOf(toppings[i].toUpperCase()));
        }
        newPizza.setCrust(Crust.BROOKLYN);
        return newPizza;
    }
    /**
     * Creates a meatzza pizza
     * @return Pizza object
     */
    @Override
    public Pizza createMeatzza() {
        Pizza newPizza = new Meatzza();
        String[] toppings = MainActivity.getToppingsList();
        for(int i = 0; i < toppings.length; i++){
            newPizza.add(Topping.valueOf(toppings[i].toUpperCase()));
        }
        newPizza.setCrust(Crust.HANDTOSSED);
        return newPizza;
    }
    /**
     * Creates a BBQChicken pizza
     * @return Pizza object
     */
    @Override
    public Pizza createBBQChicken() {
        Pizza newPizza = new BBQChicken();
        String[] toppings = MainActivity.getToppingsList();
        for(int i = 0; i < toppings.length; i++){
            newPizza.add(Topping.valueOf(toppings[i].toUpperCase()));
        }
        newPizza.setCrust(Crust.THIN);
        return newPizza;
    }

    /**
     * Creates a BuildYourOwn pizza
     * @return Pizza object
     */
    @Override
    public Pizza createBuildYourOwn() {
        Pizza newPizza = new BuildYourOwn();
        String[] toppings = MainActivity.getToppingsList();
        for(int i = 0; i < toppings.length; i++){
            newPizza.add(Topping.valueOf(toppings[i].toUpperCase()));
        }
        newPizza.setCrust(Crust.HANDTOSSED);
        return newPizza;
    }
}
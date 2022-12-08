package com.example.project5;

import java.util.ArrayList;

/**
 * The class for the buildYourOwn pizza
 * @author Jake Michalowicz, Young Ye
 */
public class BuildYourOwn extends Pizza{
    private static final double PRICE_PER_TOPPING = 1.59;

    /**
     * Adds a topping to the pizza
     * @param obj  the Topping
     * @return true if obj is a Topping object, false otherwise
     */
    @Override
    public boolean add(Object obj) {
        ArrayList<Topping> toppings = this.getToppings();
        if(obj instanceof Topping){
            Topping topping = (Topping)obj;
            toppings.add(topping);
            return true;
        }
        return false;
    }
    /**
     * Removes a topping to the pizza
     * @param obj  the Topping
     * @return true if obj is a Topping object, false otherwise
     */
    @Override
    public boolean remove(Object obj) {
        if(obj instanceof Topping){
            ArrayList<Topping> toppings = this.getToppings();
            Topping topping = (Topping)obj;
            toppings.remove(topping);
            return true;
        }
        return false;
    }
    /**
     * Returns the price of the pizza
     * @return price
     */
    @Override
    public double price() {
        double price = 0;

        if(this.getSize() == Size.SMALL){
            price = 8.99;
        }else if(this.getSize() == Size.MEDIUM){
            price = 10.99;
        }else if(this.getSize() == Size.LARGE){
            price = 12.99;
        }
        price += PRICE_PER_TOPPING * this.getToppings().size();
        return price;
    }
}

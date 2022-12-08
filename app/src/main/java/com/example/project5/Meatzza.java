package com.example.project5;

import java.util.ArrayList;

/**
 * The class for the meatzza pizzas
 * @author Jake Michalowicz, Young Ye
 */
public class Meatzza extends Pizza{

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
        if(this.getSize() == Size.SMALL){
            return 15.99;
        }else if(this.getSize() == Size.MEDIUM){
            return 17.99;
        }else if(this.getSize() == Size.LARGE){
            return 19.99;
        }
        return 0;
    }
}

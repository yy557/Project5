package com.example.project5;

import java.util.ArrayList;

/**
 * The order class
 * @author Jake Michalowicz, Young Ye
 */
public class Order implements Customizable {
    private static int latestOrderNumber = 0;
    private int orderNumber;
    private ArrayList<Pizza> pizzas = new ArrayList<>();

    /**
     * Constructor for an object of type Order
     */
    public Order(){
        latestOrderNumber++;
        orderNumber = latestOrderNumber;
    }

    /**
     * Adds an object of type Pizza to the pizzas array list
     * @param obj the Object that is being added
     * @return true if the object is an instance of Pizza, false otherwise
     */
    @Override
    public boolean add(Object obj) {
        if(obj instanceof Pizza){
             Pizza pizza = (Pizza) obj;
             pizzas.add(pizza);
             return true;
        }
        return false;
    }

    /**
     * Removes an object of type Pizza to the pizzas array list (Nothing happens if object does not exist in pizzas)
     * @param obj the Object that is being added
     * @return true if the object is an instance of Pizza, false otherwise
     */
    @Override
    public boolean remove(Object obj) {
        if(obj instanceof Pizza){
            Pizza pizza = (Pizza) obj;
            pizzas.remove(pizza);
            return true;
        }
        return false;
    }

    /**
     * Gets the order number of the Order object
     * @return the order number
     */
    public int getOrderNumber(){
        return orderNumber;
    }

    /**
     * Gets the pizzas array list
     * @return pizzas list
     */
    public ArrayList<Pizza> getPizzas(){
        return pizzas;
    }
}
